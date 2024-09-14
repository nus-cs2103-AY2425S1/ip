package darkpool.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import darkpool.task.Deadline;
import darkpool.task.Event;
import darkpool.task.Task;
import darkpool.task.Todo;


/**
 * Handles the loading and saving of tasks to and from a file.
 */

public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if the file and its directory exist, and creates them if they do not.
     *
     * @return The file object representing the data file.
     * @throws DarkpoolException If an I/O error occurs.
     */
    private File validateData() throws DarkpoolException {
        File dataFile = new File(filePath);

        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new DarkpoolException(e.getMessage());
            }
        }

        return dataFile;
    }

    /**
     * Loads tasks from the data file.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws DarkpoolException If the file is not found or an error occurs while reading the file.
     */
    public ArrayList<Task> loadData() throws DarkpoolException {
        ArrayList<Task> taskList = new ArrayList<>();
        String curTask;
        File dataFile = validateData();
        Scanner scanner;

        try {
            scanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            throw new DarkpoolException(e.getMessage());
        }

        while (scanner.hasNext()) {
            curTask = scanner.nextLine();
            taskList.add(parseTask(curTask));
        }

        scanner.close();
        return taskList;
    }

    /**
     * Saves tasks to the data file.
     *
     * @param taskList The list of tasks to be saved.
     * @throws DarkpoolException If an I/O error occurs while writing to the file.
     */
    public void saveData(TaskList taskList) throws DarkpoolException {
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(filePath);
            fileWriter.write(taskList.toFileString());
            fileWriter.close();
        } catch (IOException e) {
            throw new DarkpoolException(e.getMessage());
        }
    }

    /**
     * Parses a task from a string representation.
     *
     * @param task The string representation of the task.
     * @return The Task object parsed from the string.
     * @throws DarkpoolException If the task string is invalid.
     */
    private Task parseTask(String task) throws DarkpoolException {
        String[] taskParts = task.split(" \\| ");
        String type = taskParts[0];
        boolean isDone = taskParts[1].equals("1");
        String description = taskParts[2];
        String from;
        String to;
        String by;

        switch (type) {
        case "E" -> {
            from = taskParts[3];
            to = taskParts[4];
            return new Event(description, from, to, isDone);
        }
        case "D" -> {
            by = taskParts[3];
            return new Deadline(description, by, isDone);
        }
        case "T" -> {
            return new Todo(description, isDone);
        }
        default -> {
            System.out.println("Unknown task type: " + type);
            return null;
        }
        }
    }
}
