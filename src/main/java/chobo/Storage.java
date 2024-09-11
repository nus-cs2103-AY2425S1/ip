package chobo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * The type Storage.
 */
public class Storage {
    private final String filePath;

    /**
     * Instantiates a new Storage.
     *
     * @param filePath the file path
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load tasks into array list.
     *
     * @return the array list
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String name = parts[2];
                assert taskType.equals("T") || taskType.equals("D") 
                || taskType.equals("E") : "Task type should be one of those";

                switch (taskType) {
                case "T":
                    tasks.add(new ToDo(name, isDone));
                    break;
                case "D":
                    String by = parts[3];
                    tasks.add(new Deadline(name, isDone, by));
                    break;
                case "E":
                    String from = parts[3];
                    String to = parts[4];
                    tasks.add(new Event(name, isDone, from, to));
                    break;
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks");
        }
        
        return tasks;
    }

    /**
     * Save tasks.
     *
     * @param tasks the tasks to be saved
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Can't save tasks.");
        }
    }
}

