package blue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import blue.task.Task;
/**
 * The {@code Storage} class handles loading tasks from a file and saving tasks to a file.
 * It is responsible for persisting the state of the task list between program runs.
 */
public class Storage {

    /** The path to the file where tasks are saved and loaded from. */
    private static final String FILE_PATH = "./data/blue.txt";

    /**
     * Loads tasks from the file and adds them to the specified list.
     *
     * @param myList The list of tasks to populate from the file.
     */
    public static void loadFromFile(ArrayList<Task> myList) {
        // Ensure that myList is not null
        assert myList != null : "Task list (myList) should not be null";

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileString(line);
                myList.add(task);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading from file: " + e.getMessage());
        }
    }

    /**
     * Saves the current list of tasks to the file.
     *
     * @param myList The list of tasks to save to the file.
     */
    public static void saveToFile(ArrayList<Task> myList) {

        // Ensure that myList is not null
        assert myList != null : "Task list (myList) should not be null";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : myList) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }
}
