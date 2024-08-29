package bocchi.storage;

import bocchi.exception.BocchiException;
import bocchi.task.Task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a loader and saver for the task list.
 * It uses serialization to save and load the task list.
 */
public class Storage {

    /**
     * The directory where data is stored.
     */
    private final Path DATA_DIRECTORY = Path.of("data");
    /**
     * The file path of the serialized task list.
     */
    private final Path FILE_PATH = DATA_DIRECTORY.resolve("tasks.ser");

    /**
     * The constructor.
     */
    public Storage() {
        if (!Files.exists(DATA_DIRECTORY)) {  // create the data directory if it does not exist
            try {
                Files.createDirectories(DATA_DIRECTORY);
            } catch (IOException e) {
                throw new RuntimeException(e);  // this should not happen
            }
        }
    }

    /**
     * Loads the task list from the file.
     *
     * @return The task list. Returns null if the file does not exist or cannot be read.
     */
    public List<Task> load() throws BocchiException {
        List<Task> savedTasks = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(FILE_PATH.toString());
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Object savedObject = in.readObject();
            if (savedObject instanceof List<?> savedList) {  // first check if the object is a list
                for (Object task : savedList) {
                    if (!(task instanceof Task)) {  // then check if the elements in the list are tasks
                        throw new BocchiException("There is a problem with the saved data.");
                    } else {
                        savedTasks.add((Task) task);
                    }
                }
            } else {
                throw new BocchiException("There is a problem with the saved data.");
            }
        } catch (FileNotFoundException e) {
            return new ArrayList<>();  // return an empty list if the file does not exist
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);  // this should not happen
        } catch (IOException e) {
            try {
                Files.delete(FILE_PATH);  // delete the file if it cannot be read
            } catch (IOException ex) {
                throw new RuntimeException(ex); // this should not happen
            }
            throw new BocchiException("There is a problem with the saved data.");
        }
        return savedTasks;
    }


    /**
     * Saves the task list to the file.
     *
     * @param tasks The task list.
     */
    public void save(List<Task> tasks) throws BocchiException {
        try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH.toString());
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(tasks);
        } catch (IOException e) {
            throw new BocchiException("There is a problem with saving the data.");
        }
    }

}
