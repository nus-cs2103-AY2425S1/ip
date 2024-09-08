package echo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Handles file operations for storing and retrieving a TaskList object.
 * It manages the initialization of the data file, reading data from the file,
 * and writing data to the file.
 */
public class Storage {
    private static final String DATA_PATH = "data/data.txt";

    /**
     * Initializes the data file.
     * If the file already exists, checks if it's empty and writes an empty TaskList to it.
     * If the file does not exist, creates the necessary directory and file, and then
     * writes an empty TaskList to the file.
     */
    public static void init() {
        Path filePath = Paths.get(Storage.DATA_PATH);

        // If there is already a data.txt file, simply exit
        if (Files.exists(filePath)) {
            setEmptyFile();
            return;
        }

        // Creat a new File object and put a empty task list in it
        File f = new File(DATA_PATH);
        try {
            File parentDir = f.getParentFile();
            parentDir.mkdir();
            f.createNewFile();

            // Put a empty task list in the file created
            TaskList allTasks = new TaskList();
            Storage.setData(allTasks);

        } catch (IOException e) {
            System.out.println("Init Error: " + e.getMessage());
        }
    }

    /**
     * Checks if the data file is empty. If it is, writes an empty TaskList
     * to the file.
     */
    public static void setEmptyFile() {
        File f = new File(Storage.DATA_PATH);

        if (f.length() == 0) {
            TaskList allTasks = new TaskList();
            Storage.setData(allTasks);
        }
    }

    /**
     * Writes the provided TaskList object to the data file.
     *
     * @param taskList the TaskList object to be written to the file.
     */
    public static void setData(TaskList taskList) {
        try {
            FileOutputStream fileStream = new FileOutputStream(Storage.DATA_PATH);
            ObjectOutputStream os = new ObjectOutputStream(fileStream);

            os.writeObject(taskList);

            // close ObjectOutputStream
            os.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the TaskList object from the data file.
     *
     * @return the TaskList object read from the file, or an empty TaskList
     *         if the file is empty or an error occurs.
     */
    public static TaskList getData() {
        TaskList allTasks = null;
        try {
            FileInputStream fileStream = new FileInputStream(Storage.DATA_PATH);
            ObjectInputStream outputStream = new ObjectInputStream(fileStream);
            allTasks = (TaskList) outputStream.readObject();
            outputStream.close();
            return allTasks;
        } catch (IOException e) {
            System.out.println("Get Data Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Invalid Class Casting: " + e.getMessage());
        }

        return allTasks;
    }

}
