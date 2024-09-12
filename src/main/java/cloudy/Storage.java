package cloudy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

/**
 * The storage class handles the reading and writing of task data to and from a file,
 * ensuring that tasks are saved between sessions.
 */
public class Storage {
    private static String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     * @param filePath The path to the file where the tasks will be stored.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path should not be null";
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to the file specified by filePath.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasksToFile(ArrayList<Task> tasks) {
        checkFileExists();
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error occurred while saving tasks.");
            e.printStackTrace();
        }
    }

    /**
     * Loads the list of tasks from the file specified by filePath.
     *
     * @return A list of tasks loaded from the file, stored in an ArrayList. If the file
     * is empty or does not exist, an empty list is returned.
     */
    public static ArrayList<Task> loadTasksFromFile() {
        assert filePath != null && !filePath.trim().isEmpty();
        checkFileExists();
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine()) != null) {
                Task task = Parser.parseTaskFromFile(line);
                if (task != null) {
                    tasks.add(task);
                } else {
                    System.out.println("Skipping invalid task" + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous task file found. Creating one...");
        } catch (IOException e) {
            System.out.println("Error occurred while loading tasks.");
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Check if the file and its parent directories exist. If they do not exist, it creates them.
     */
    public static void checkFileExists() {
        assert filePath != null && !filePath.trim().isEmpty();
        File file = new File(filePath);
        File directory = file.getParentFile();

        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error occurred when creating the file.");
            e.printStackTrace();
        }
    }


}
