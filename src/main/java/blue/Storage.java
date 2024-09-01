package blue;

import blue.task.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The blue.Storage class deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    private static final String FILE_PATH = "./data/blue.txt";

    /**
     * Loads tasks into array list from file.
     * @param myList list of tasks.
     * @throws IOException error reading from file
     */
    public static void loadFromFile(ArrayList<Task> myList) {

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
     * Writes to file after editing task.
     * @param myList list of tasks.
     * @throws IOException error reading from file
     */
    public static void saveToFile(ArrayList<Task> myList) {
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
