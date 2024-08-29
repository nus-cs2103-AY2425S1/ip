package monique.storage;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import monique.task.Task;


/**
 * The <code>Storage</code> class handles the reading from and writing to a file that stores serialized
 * <code>Task</code> objects.
 * It provides methods to save and load tasks, and ensures that the file for storage exists.
 */
public class Storage {
    private final String filePath;
    private final File dbFile;

    /**
     * Constructs a new <code>Storage</code> object with the specified file path.
     * Initializes the file for storage, creating it if it does not exist.
     *
     * @param filePath The path of the file to be used for storage.
     * @throws IOException If an I/O error occurs while creating the file.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.dbFile = this.getDbFile();
    }

    /**
     * Returns the current working directory path of the application.
     *
     * @return The absolute path of the current working directory.
     */
    public static String getCurrentPath() {
        String s = Paths.get("").toAbsolutePath().toString();
        return s;
    }

    /**
     * Returns the path to the default data file for tasks.
     *
     * @return The path of the data file as a string.
     */
    public static String getDataPath() {
        return String.valueOf(Paths.get("data/tasks.txt"));
    }

    /**
     * Returns the <code>File</code> object representing the storage file.
     * Creates the file and its parent directories if they do not exist.
     *
     * @return The <code>File</code> object representing the storage file.
     * @throws IOException If an I/O error occurs while creating the file.
     */
    public File getDbFile() throws IOException {
        File dbFile = new File(String.valueOf(Paths.get(this.filePath)));
        if (!dbFile.exists()) {
            dbFile.getParentFile().mkdirs();
            dbFile.createNewFile();
        }
        return dbFile;
    }

    /**
     * Saves the given list of tasks to the storage file.
     * Each task is serialized and written to the file.
     *
     * @param taskList The list of tasks to be saved.
     */
    public void save(ArrayList<Task> taskList) {
        try {
            FileOutputStream fos = new FileOutputStream(this.getDbFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Task task : taskList) {
                oos.writeObject(task);
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the list of tasks from the storage file.
     * Deserializes each task and adds it to the list.
     * If the file is empty or does not exist, an empty list is returned.
     *
     * @return The list of tasks loaded from the storage file.
     */
    public ArrayList<Task> loadData() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            FileInputStream fin = new FileInputStream(this.getDbFile());
            ObjectInputStream ois = new ObjectInputStream(fin);
            Object o;
            while (true) {
                try {
                    o = ois.readObject();
                    if (o instanceof Task) {
                        taskList.add((Task) o);
                    } else {
                        System.err.println("Unexpected objects found in database");
                    }
                } catch (EOFException ex) {
                    break;
                }
            }
        } catch (EOFException eof) {
            //empty database from the start
            return taskList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return taskList;
    }



}
