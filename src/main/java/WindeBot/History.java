package WindeBot;

import Tasks.Task;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Todos;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class History {
    private static final String WINDE_FILE = "./src/main/java/WindeTasks.txt";
    private static String filePath;

    History(String filePath) {
        this.filePath = filePath;
    }

    History() {
        this.filePath = WINDE_FILE;
    }

    public static ArrayList<Task> load() {
        File file = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch(IOException ioe) {
            System.out.println("Error in creating file" + ioe.getMessage());
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            String complete = "X";
            Task task;
            while (line != null) {
                if (line.startsWith("T")) {
                    String[] instr = line.split(" \\| ");
                    task = new Todos(instr[2], (complete.equals(instr[1]) ? true : false));
                } else if (line.startsWith("D")) {
                    String[] instr = line.split(" \\| ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDateTime deadline = LocalDateTime.parse(instr[3], formatter);
                    task = new Deadline(instr[2], (complete.equals(instr[1]) ? true : false), deadline);
                } else {
                    String[] instr = line.split(" \\| ");
                    String[] when = instr[3].split(" - ");
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalDateTime start = LocalDateTime.parse(when[0], dateFormatter);
                    LocalDateTime end = LocalDateTime.parse(when[1], dateFormatter);
                    task = new Event(instr[2], (complete.equals(instr[1]) ? true : false), start, end);
                }
                taskList.add(task);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            System.out.println("Error Loading Tasks to File: " + ioe.getMessage());
        }
        return taskList;
    }

    public static void save(ArrayList<Task> reminder) {
        try {
            FileWriter fw = new FileWriter(WINDE_FILE);
            for (Task tasks : reminder) {
                fw.write(tasks.toString() + "\n");
            }
            fw.close();
        } catch(IOException ioe) {
            System.out.println("Error Saving Tasks to File: " + ioe.getMessage());
        }
    }
}
