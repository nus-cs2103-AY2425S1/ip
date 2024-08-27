package applemazer;

import tasks.*;
import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    /**
     * Constructor for the Storage object.
     * @param filePath The file where the task list is saved to or loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves task list information to the stored filepath.
     * A new filepath will be created if the stored filepath does not exist.
     */
    public void save() {
        System.out.println ("Saving data...");
        String directoryPath = "./data";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            System.out.println();
            System.out.println("Directory ./data does not exist.\n" +
                               "Creating new directory ./data... ");
            if (directory.mkdir()) {
                System.out.println("Directory ./data created successfully.\n");
            }
        }

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File ./data/Applemazer.ser does not exist.\n" +
                               "Creating new file ./data/Applemazer.ser... ");
            try {
                if (file.createNewFile()) {
                    System.out.println("File ./data/Applemazer.ser created successfully.\n");
                }
            } catch (IOException e) {
                System.err.println("File ./data/Applemazer.ser could not be created.");
            }
        }

        try (ObjectOutputStream str = new ObjectOutputStream(new FileOutputStream(filePath))) {
            str.writeObject(Applemazer.tasks.getList());
            System.out.println ("Save successful.\n");
        } catch (IOException e) {
            System.err.println("Save unsuccessful. File might be corrupted.\n");
        }
    }

    /**
     * Loads task list information from the stored filepath.
     * If the stored filepath does not exist, a new task list will be returned.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> load() {
        String directoryPath = "./data";
        File directory = new File(directoryPath);
        File file = new File(filePath);

        if (directory.exists() && file.exists()) {
            System.out.println ("Loading data...");
            try (ObjectInputStream str = new ObjectInputStream(new FileInputStream(filePath))) {
                ArrayList<Task> tasks = (ArrayList<Task>) str.readObject();
                System.out.println ("Loading successful.\n");
                return tasks;
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Loading unsuccessful. File might be corrupted.");
                System.out.println("Starting new save...\n");
                return new ArrayList<>();
            }
        }

        System.out.println ("Saved file could not be found. Starting new save...\n");
        return new ArrayList<>();
    }
}
