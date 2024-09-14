package opus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles the loading and saving of tasks from/to a file.
 * It reads tasks from a file to initialize the task list and writes tasks to
 * the file to save the current state of the task list.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     * If the file does not exist or there is an error, an empty list is returned.
     *
     * @return An ArrayList of tasks loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                switch (taskType) {
                    case "T":
                        tasks.add(new ToDo(parts[2]));
                        break;
                    case "D":
                        tasks.add(new Deadline(parts[2], parts[3]));
                        break;
                    case "E":
                        tasks.add(new Event(parts[2], parts[3], parts[4]));
                        break;
                }
                if (isDone) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
            scanner.close();
            return tasks;
        } catch (IOException e) {
            return tasks;
        }
    }

    /**
     * Saves the current list of tasks to the file.
     * If the file or directories do not exist, they are created.
     *
     * @param tasks The list of tasks to save to the file.
     */
    public void save(ArrayList<Task> tasks) {
        File file = new File(filePath);

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toSaveFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            // Log or handle the exception if saving fails
            return;
        }
    }
}
