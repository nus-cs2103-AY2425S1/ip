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
public class TaskListLoaderSaver {

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
    public TaskListLoaderSaver() {
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
    public List<Task> load() {
        List<Task> savedTasks = new ArrayList<>();

        try (FileInputStream fileIn = new FileInputStream(FILE_PATH.toString());
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Object savedObject = in.readObject();
            if (savedObject instanceof List<?> savedList) {  // first check if the object is a list
                for (Object task : savedList) {
                    if (!(task instanceof Task)) {  // then check if the elements in the list are tasks
                        throw new IOException("The saved object is not a list of tasks.");
                    } else {
                        savedTasks.add((Task) task);
                    }
                }
            } else {
                throw new IOException("The saved object is not a list.");
            }
        } catch (FileNotFoundException e) {
            return null;  // return null if the file does not exist
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);  // this should not happen
        } catch (IOException e) {
            try {
                Files.delete(FILE_PATH);  // delete the file if it cannot be read
            } catch (IOException ex) {
                throw new RuntimeException(ex); // this should not happen
            }
            return null;  // return null if the file cannot be read
        }
        return savedTasks;
    }


    /**
     * Saves the task list to the file.
     *
     * @param tasks The task list.
     * @return true if the task list is saved successfully, false otherwise.
     * @throws IOException If an I/O error occurs.
     */
    public boolean save(List<Task> tasks) {
        try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH.toString());
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
            return false;  // return false if an I/O error occurs
        }
        return true;
    }

}
