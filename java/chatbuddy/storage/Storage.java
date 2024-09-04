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

    private String filePath;

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
        try {
            File file = new File(filePath);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" \\| ");
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    Task task;

                    switch (type) {
                        case "T":
                            task = new ToDo(description);
                            break;
                        case "D":
                            task = new Deadline(description, parts[3]);
                            break;
                        case "E":
                            task = new Event(description, parts[3], parts[4]);
                            break;
                        default:
                            throw new ChatBuddyException("Invalid task type in file.");
                    }

                    if (isDone) {
                        task.markAsDone();
                    }

                    tasks.add(task);
                }
                scanner.close();
            } else {
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new ChatBuddyException("An error occurred while loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves tasks to the storage file.
     *
     * @param tasks The list of tasks to save.
     * @throws ChatBuddyException If there is an error while saving the tasks.
     */
    public void saveTasks(ArrayList<Task> tasks) throws ChatBuddyException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new ChatBuddyException("An error occurred while saving tasks: " + e.getMessage());
        }
    }
}
