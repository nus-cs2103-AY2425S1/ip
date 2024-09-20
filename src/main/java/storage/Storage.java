package storage;


import java.io.File;
import java.io.IOException;

/**
 * Represents the storage mechanism for saving and loading data to and from a file.
 */
public class Storage {

    protected File storageFile;

    /**
     * Constructs a {@code Storage} object with the specified file.
     *
     * @param storageFile The file used for storing and retrieving data.
     */
    public Storage(File storageFile) {
        this.storageFile = storageFile;
    }

    /**
     * Creates a {@code Storage} object with the specified file path.
     * If the directory or file does not exist, it will be created.
     *
     * @param path The path of the storage file.
     * @return A {@code Storage} object initialized with the specified file.
     */
    public static Storage createStorage(String path) {
        File file = new File(path);
        File directory = file.getParentFile();

        if (!directory.exists()) {
            directory.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Storage(file);
    }

    /**
     * Returns the storage file used for saving and loading data.
     *
     * @return The storage file.
     */
    public File getStorageFile() {
        return storageFile;
    }
}
