package chatbuddy.storage;

import chatbuddy.exception.ChatBuddyException;
import chatbuddy.task.Deadline;
import chatbuddy.task.Event;
import chatbuddy.task.Task;
import chatbuddy.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of tasks to and from the storage file.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file used for storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks loaded from the file.
     * @throws ChatBuddyException If there is an error while loading the tasks.
     */
    public ArrayList<Task> load() throws ChatBuddyException {
        File file = new File(filePath);
        if (!file.exists()) {
            createFileAndDirectories(file);
            return new ArrayList<>();
        }
        return loadTasksFromFile(file);
    }

    /**
     * Saves tasks to the storage file.
     *
     * @param tasks The list of tasks to save.
     * @throws ChatBuddyException If there is an error while saving the tasks.
     */
    public void saveTasks(ArrayList<Task> tasks) throws ChatBuddyException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            throw new ChatBuddyException("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file.
     *
     * @param file The file to read tasks from.
     * @return A list of tasks read from the file.
     * @throws ChatBuddyException If there is an error while loading the tasks.
     */
    private ArrayList<Task> loadTasksFromFile(File file) throws ChatBuddyException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tasks.add(parseTaskFromLine(line));
            }
        } catch (IOException e) {
            throw new ChatBuddyException("An error occurred while reading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Parses a task from a line in the file.
     *
     * @param line The line of text representing a task.
     * @return The Task object created from the line.
     * @throws ChatBuddyException If there is an error while parsing the task.
     */
    private Task parseTaskFromLine(String line) throws ChatBuddyException {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = createTask(type, description, parts);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Creates a task object based on the type and parts from the file.
     *
     * @param type        The type of task ("T", "D", "E").
     * @param description The task description.
     * @param parts       The line parts containing additional details.
     * @return The Task object created.
     * @throws ChatBuddyException If an invalid task type is encountered.
     */
    private Task createTask(String type, String description, String[] parts) throws ChatBuddyException {
        switch (type) {
        case "T":
            return new ToDo(description);
        case "D":
            return new Deadline(description, parts[3]);
        case "E":
            return new Event(description, parts[3], parts[4]);
        default:
            throw new ChatBuddyException("Invalid task type in file.");
        }
    }

    /**
     * Creates the file and its parent directories if they do not exist.
     *
     * @param file The file to be created.
     * @throws ChatBuddyException If there is an error creating the file or directories.
     */
    private void createFileAndDirectories(File file) throws ChatBuddyException {
        try {
            File directory = new File(file.getParent());
            if (!directory.exists()) {
                directory.mkdirs();
            }
            file.createNewFile();
        } catch (IOException e) {
            throw new ChatBuddyException("An error occurred while creating the file: " + e.getMessage());
        }
    }
}
