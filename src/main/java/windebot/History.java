package windebot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todos;

/**
 * The History class handles saving and loading the reminder list to and from a text file.
 * It is used to persist the task data across different sessions of the application.
 */

public class History {
    private static final String WINDE_FILE = "./src/main/java/WindeTasks.txt";
    private static String filePath;

    /**
     * Constructs a History object with the specified file path.
     *
     * @param filePath The path to the file where the reminder list is stored.
     */

    History(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Constructs a History object with the default file path.
     */

    History() {
        this.filePath = WINDE_FILE;
    }

    /**
     * Loads the reminder list from the text file.
     *
     * @return An ArrayList of tasks loaded from the file.
     */

    public static ArrayList<Task> load() {
        File file = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException ioe) {
            System.out.println("Error in creating file" + ioe.getMessage());
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            while (line != null) {
                taskList.add(readLine(line));
                line = br.readLine();
            }
        } catch (IOException ioe) {
            System.out.println("Error Loading Tasks to File: " + ioe.getMessage());
        }
        return taskList;
    }

    /**
     * Reads a line from the file.
     *
     * @param line The String of the file's line which the data is stored.
     * @return A task which the file line was coding for.
     */

    public static Task readLine(String line) {
        String complete = "X";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Task task;
        if (line.startsWith("T")) {
            String[] instr = line.split(" \\| ");
            task = new Todos(instr[2], (complete.equals(instr[1]) ? true : false));
        } else if (line.startsWith("D")) {
            String[] instr = line.split(" \\| ");
            LocalDateTime deadline = LocalDateTime.parse(instr[3], formatter);
            task = new Deadline(instr[2], (complete.equals(instr[1]) ? true : false), deadline);
        } else {
            String[] instr = line.split(" \\| ");
            String[] when = instr[3].split(" - ");
            LocalDateTime start = LocalDateTime.parse(when[0], formatter);
            LocalDateTime end = LocalDateTime.parse(when[1], formatter);
            task = new Event(instr[2], (complete.equals(instr[1]) ? true : false), start, end);
        }
        return task;
    }

    /**
     * Saves the reminder list to the text file. Default Winde Text File
     *
     * @param reminder The list of tasks to be saved.
     */

    public static void save(ArrayList<Task> reminder) {
        try {
            FileWriter fw = new FileWriter(WINDE_FILE);
            for (Task tasks : reminder) {
                fw.write(tasks.toString() + "\n");
            }
            fw.close();
        } catch (IOException ioe) {
            System.out.println("Error Saving Tasks to File: " + ioe.getMessage());
        }
    }
}
