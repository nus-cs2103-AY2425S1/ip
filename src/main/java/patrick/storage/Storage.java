package patrick.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import patrick.tasklist.Deadline;
import patrick.tasklist.Event;
import patrick.tasklist.Task;
import patrick.tasklist.ToDo;

/**
 * The {@code Storage} class handles the reading and writing of task data to and from a file.
 * It supports loading tasks from a file, adding, deleting, and saving tasks.
 */
public class Storage {
    private static final ArrayList<Task> list = new ArrayList<>();
    private static String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath the path to the file where tasks are stored.
     * @throws NullPointerException if the file path is null.
     */
    public Storage(String filePath) throws NullPointerException {
        if (filePath != null) {
            Storage.filePath = filePath;
        } else {
            throw new NullPointerException("FilePath cannot be null");
        }
    }

    /**
     * Loads tasks from the specified file. If the file does not exist, a new file is created.
     *
     * @return an {@code ArrayList} of tasks loaded from the file.
     * @throws StorageOperationException if there is an error during the file operation.
     */
    public ArrayList<Task> load() throws StorageOperationException {
        try {
            readTasks(filePath);
        } catch (FileNotFoundException e) {
            File newFile = new File(filePath);
            try {
                newFile.createNewFile();
            } catch (IOException ex) {
                throw new StorageOperationException(ex.getMessage());
            }
        } catch (IllegalValueException e) {
            throw new StorageOperationException(e.getMessage());
        }
        return list;
    }

    /**
     * Returns the list of tasks.
     *
     * @return an {@code ArrayList} of tasks.
     */
    public static ArrayList<Task> getList() {
        return list;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added.
     */
    public static void addList(Task task) {
        list.add(task);
    }

    /**
     * Deletes a task from the list by index.
     *
     * @param index the 1-based index of the task to be deleted.
     */
    public static void deleteItem(int index) {
        assert index >= 0 : "index cannot be less than 0";
        list.remove(index - 1);
    }

    /**
     * Reads tasks from the file and populates the task list.
     *
     * @param filePath the path to the file containing the tasks.
     * @throws FileNotFoundException if the file is not found.
     * @throws IllegalValueException if the file contains invalid data.
     */
    private static void readTasks(String filePath) throws FileNotFoundException, IllegalValueException {
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        Task currTask;
        while (s.hasNext()) {
            String taskString = s.nextLine();
            if (taskString.startsWith("T ")) {
                String taskDescription = taskString.substring(8);
                currTask = new ToDo(taskDescription);
                list.add(currTask);
                if (taskString.substring(4).startsWith("X")) {
                    currTask.markAsDone();
                }
            } else if (taskString.startsWith("D ")) {
                String taskDescription = taskString.substring(8);
                taskDescription = taskDescription.substring(0, taskDescription.indexOf("|") - 1);
                String deadline = taskString.substring(8).replace(taskDescription, "").replace(" | ", "");
                currTask = new Deadline(taskDescription, deadline);
                list.add(currTask);
                if (taskString.substring(4).startsWith("X")) {
                    currTask.markAsDone();
                }
            } else if (taskString.startsWith("E ")) {
                String taskDescription = taskString.substring(8);
                taskDescription = taskDescription.substring(0, taskDescription.indexOf("|") - 1);
                String tempFrom = taskString.substring(8).replace(taskDescription + " ", "").substring(2);
                String to = tempFrom.substring(tempFrom.indexOf("-") + 1);
                String from = tempFrom.replace("-" + to, "");
                currTask = new Event(taskDescription, from, to);
                list.add(currTask);
                if (taskString.substring(4).startsWith("X")) {
                    currTask.markAsDone();
                }
            } else {
                throw new IllegalValueException("Invalid File");
            }
        }
    }

    /**
     * Appends the specified text to the file.
     *
     * @param text the text to be appended to the file.
     * @throws IOException if an I/O error occurs.
     */
    public static void appendToFile(String text) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(text);
        fileWriter.close();
    }

    /**
     * Writes the current list of tasks to the file, overwriting its contents.
     *
     * @throws IOException if an I/O error occurs.
     */
    public static void writeToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                String temp = list.get(i).toString();
                fileWriter.write(temp);
            } else {
                String temp = list.get(i).toString();
                fileWriter.write(temp + "\n");
            }
        }
        fileWriter.close();
    }

    /**
     * Exception class used for handling storage operation errors.
     */
    public static class StorageOperationException extends Exception {
        /**
         * Constructs a new {@code StorageOperationException} with the specified detail message.
         *
         * @param message the detail message.
         */
        public StorageOperationException(String message) {
            super(message);
        }
    }

    /**
     * Exception class used for handling illegal values in the task data.
     */
    public static class IllegalValueException extends Exception {
        /**
         * Constructs a new {@code IllegalValueException} with the specified detail message.
         *
         * @param message should contain relevant information on the failed constraint(s).
         */
        public IllegalValueException(String message) {
            super(message);
        }
    }
}
