package revir.system;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import revir.tasks.Task;

/**
 * The Storage class represents a storage mechanism for saving and loading
 * tasks.
 * It provides methods to load tasks from a file and save tasks to a file.
 */
public class Storage {
    private Path filePath;

    public Storage(Path path) {
        this.filePath = path;
    }

    /**
     * Loads tasks from a file and returns them as an ArrayList.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws IOException            If an I/O error occurs while reading the file.
     * @throws ClassNotFoundException If the class of a serialized object cannot be
     *                                found.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> loadFromFile() throws IOException, ClassNotFoundException {
        if (Files.exists(this.filePath)) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.filePath.toString()));
            ArrayList<Task> result = (ArrayList<Task>) ois.readObject();
            ois.close();
            return result;
        }
        return new ArrayList<Task>();
    }

    /**
     * Saves the given list of tasks to a file.
     *
     * @param tasks the list of tasks to be saved
     * @throws IOException if an I/O error occurs while creating or writing to the
     *                     file
     */
    public void saveToFile(ArrayList<Task> tasks) throws IOException {
        Files.createDirectories(this.filePath.getParent());

        if (!Files.exists(this.filePath)) {
            Files.createFile(this.filePath);
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.filePath.toFile()));
        oos.writeObject(tasks);
        oos.close();
    }
}
