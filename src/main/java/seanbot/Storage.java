package seanbot;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import seanbot.Tasks.Deadline;
import seanbot.Tasks.Event;
import seanbot.Tasks.Task;
import seanbot.Tasks.Todo;

public class Storage {

    private String PATH;

    public Storage(String PATH) {
        assert PATH != null && !PATH.isEmpty() : "File path cannot be null or empty";
        this.PATH = PATH;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(PATH);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                Task task = null;
                switch (parts[0]) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                    case "D":
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                }
                if (task != null && parts[1].equals("1")) {
                    task.markAsDone();
                }
                if (task != null) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        try (FileWriter fw = new FileWriter(PATH);) {
            for (Task task : tasks) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
            fw.close(); 
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
