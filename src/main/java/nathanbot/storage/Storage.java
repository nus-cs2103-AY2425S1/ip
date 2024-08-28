package nathanbot.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import nathanbot.tasks.Task;

/**
 * Cleaned up using Copilot to follow Google's Java Style Guide,
 * while keeping indentations to be 4 spaces.
 */
public class Storage {
    private final String txtFileName;
    private final String datFileName;
    private final String storageDirectory;

    /**
     * Constructs a Storage object with specified directory and file names.
     *
     * @param storageDirectory The directory where files are stored.
     * @param txtFileName The name of the text file for storing tasks.
     * @param datFileName The name of the binary file for storing tasks.
     */
    public Storage(String storageDirectory, String txtFileName, String datFileName) {
        this.txtFileName = txtFileName;
        this.datFileName = datFileName;
        this.storageDirectory = storageDirectory;
    }

    /**
     * Saves the list of tasks to both text and binary files.
     *
     * @param taskList The list of tasks to be saved.
     */
    public void saveTasksToFile(ArrayList<Task> taskList) {
        File dataDir = new File(storageDirectory);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }   
        try (
            FileWriter writer = new FileWriter(txtFileName);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(datFileName))
        ) {
            for (Task task : taskList) {
                writer.write(task.toString() + "\n");
            }
            oos.writeObject(taskList);
        } catch (IOException e) {
            System.err.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads the list of tasks from the binary file.
     *
     * @return The list of tasks loaded from the file.
     */
    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(datFileName))) {
            taskList.addAll((ArrayList<Task>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("An error occurred while loading tasks from file: " + e.getMessage());
        }
        return taskList;
    }
}