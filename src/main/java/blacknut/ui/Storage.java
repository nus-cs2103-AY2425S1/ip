package blacknut.ui;

import java.io.*;
import java.util.ArrayList;

/**
 * The Storage class handles the loading and saving of tasks to and from a file.
 */
class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks will be saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     *
     * @return An ArrayList of tasks loaded from the file.
     */
    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }

        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileFormat(line);
                tasks.add(task);
            }
            reader.close();
        } catch (java.io.IOException | BlacknutExceptions.IncorrectFormatException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }


    /**
     * Saves the current list of tasks to the file specified by the file path.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(filePath));
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
            writer.close();
        } catch (java.io.IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}