package chatbaby;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the loading and saving of tasks to and from a file.
 * This class handles reading tasks from a file and writing tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws ChatBabyException If an error occurs while reading the file or parsing tasks.
     */
    public ArrayList<Task> load() throws ChatBabyException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // Return empty list if file doesn't exist
        }

        try (Scanner scanner = new Scanner(file)) {
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                try {
                    tasks.add(Task.fromFileText(line)); // Handle corrupted data inside the method
                } catch (Exception e) {
                    throw new ChatBabyException("Error while reading line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new ChatBabyException("Error while loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the tasks to the file specified by the file path.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws ChatBabyException If an error occurs while writing to the file.
     */
    public void save(TaskList tasks) throws ChatBabyException {
        try {
            Path parentDir = Path.of(filePath).getParent();
            if (parentDir != null) {
                Files.createDirectories(parentDir);  // Creates the directory if it doesn't exist
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            for (Task task: tasks.getTasks()) {
                bw.write(task.toFileText());
                bw.newLine();
            }
            bw.close();  // Close BufferedWriter to ensure all data is written
        } catch (IOException e) {
            throw new ChatBabyException("Error while saving tasks: " + e.getMessage());
        }
    }
}
