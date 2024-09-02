package wenjigglybot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private final String filePath;

    /**
     * Constructs a {@link Storage} object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file into the provided {@link TaskList}.
     * If the file does not exist, it prints a message and returns.
     *
     * @param tasks The {@link TaskList} to load tasks into.
     * @throws WenJigglyBotException If an error occurs while reading the file.
     */
    public void load(TaskList tasks) throws WenJigglyBotException {
        File file = new File(filePath);

        // Check if the file exists
        if (!file.exists()) {
            System.out.println("No saved tasks found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Parser.parseTask(line);
                if (task != null) {
                    tasks.addTask(task);
                }
            }
            System.out.println("Tasks loaded from " + file.getPath());
        } catch (IOException e) {
            throw new WenJigglyBotException("An error occurred while loading tasks from file: " + e.getMessage());
        }
    }

    /**
     * Saves the tasks from the provided {@link TaskList} to a file.
     * Creates the directory if it does not exist.
     *
     * @param tasks The {@link TaskList} containing tasks to be saved.
     */
    public static void saveTasksToFile(TaskList tasks) {
        String tasksString = tasksToString(tasks);
        // Create a File object for the directory
        File directory = new File("./data");

        // If the directory doesn't exist, create it
        if (!directory.exists()) {
            // This will create the directory and any necessary but nonexistent parent directories
            boolean mkdirs = directory.mkdirs();
            if (!mkdirs) {
                System.out.println("Failed to make directory!");
            }
        }
        try (FileWriter writer = new FileWriter("./data/data.txt")) {
            writer.write(tasksString);
            System.out.println("Tasks saved to " + "data.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Converts the {@link TaskList} into a string representation suitable for saving to a file.
     *
     * @param tasks The {@link TaskList} to be converted.
     * @return A string representation of the tasks.
     */
    private static String tasksToString(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}