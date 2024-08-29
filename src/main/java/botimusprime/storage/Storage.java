package botimusprime.storage;

import botimusprime.tasks.Task;

import java.io.*;
import java.util.ArrayList;

/**
 * The Storage class handles the reading and writing of tasks to and from disk.
 * It manages the storage location and file operations, allowing the chatbot
 * to persist task data between sessions.
 */
public class Storage {
    private final String DIRECTORY = "./data";
    private String fileName;

    /**
     * Constructs a Storage instance with the specified file name.
     * The file will be located in the specified directory.
     *
     * @param fileName The name of the file where tasks are stored.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Saves the given TaskList to disk.
     * The tasks are stored in a file, with each task written on a new line.
     *
     * @param taskList The TaskList containing tasks to be saved to disk.
     */
    public void saveToDisk(botimusprime.tasks.TaskList taskList) {
        File dir = new File(DIRECTORY);
        File file = new File(dir, fileName);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : taskList.getTasks()) {
                writer.write(task.saveString() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the tasks from disk into a TaskList.
     * If the file does not exist, an empty TaskList is returned.
     * If the file exists, it reads each line, converts it to a Task,
     * and adds it to the TaskList.
     *
     * @return A TaskList containing the tasks loaded from disk.
     */
    public botimusprime.tasks.TaskList loadFromDisk() {
        File file = new File(DIRECTORY, fileName);

        if (!file.exists()) {
            return new botimusprime.tasks.TaskList(fileName);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            ArrayList<Task> tasks = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromString(line);
                tasks.add(task);
            }
            return new botimusprime.tasks.TaskList(tasks, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new botimusprime.tasks.TaskList(fileName);
        }
    }
}