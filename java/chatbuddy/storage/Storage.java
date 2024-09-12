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

    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";
    private static final String TASK_DONE = "1";
    private static final String TASK_NOT_DONE = "0";

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
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    tasks.add(parseTask(scanner.nextLine()));
                }
            } catch (IOException | ChatBuddyException e) {
                throw new ChatBuddyException("An error occurred while loading tasks: " + e.getMessage());
            }
        } else {
            createStorageFile(file);
        }

        return tasks;
    }

    /**
     * Parses a line from the file into a Task object.
     *
     * @param line The line representing the task.
     * @return A Task object corresponding to the line.
     * @throws ChatBuddyException If the task type is invalid.
     */
    private Task parseTask(String line) throws ChatBuddyException {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals(TASK_DONE);
        String description = parts[2];

        Task task;
        switch (type) {
        case TODO_TYPE:
            task = new ToDo(description);
            break;

        case DEADLINE_TYPE:
            task = new Deadline(description, parts[3]);
            break;

        case EVENT_TYPE:
            task = new Event(description, parts[3], parts[4]);
            break;

        default:
            throw new ChatBuddyException("Invalid task type in file: " + type);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Creates the storage file and necessary directories if they do not exist.
     *
     * @param file The file to create.
     * @throws ChatBuddyException If an error occurs during file creation.
     */
    private void createStorageFile(File file) throws ChatBuddyException {
        try {
            File directory = new File(file.getParent());
            if (!directory.exists()) {
                directory.mkdirs();
            }
            file.createNewFile();
        } catch (IOException e) {
            throw new ChatBuddyException("An error occurred while creating storage file: " + e.getMessage());
        }
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
}
