package deez;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Storage class for reading and writing Deez objects to a file.
 */
public class Storage {
    /**
     * Final constant representing the name of the storage file.
     */
    static final String fileName = "deez.txt";

    private String filePath;

    /**
     * Checks if the file exists at the given path.
     *
     * @return True if the file exists, false otherwise
     */
    private boolean fileExists() {
        return new File(filePath + File.separator + fileName).exists();
    }

    /**
     * Initializes a Storage object with a specified file path.
     *
     * @param filePath The path to use for storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if the file is loaded and ready to be read.
     *
     * @return True if the file exists, false otherwise
     */
    public boolean canLoad() {
        return fileExists();
    }

    /**
     * Loads a Deez object from the storage file.
     *
     * @return The loaded Deez object
     * @throws IOException            If there's an issue reading the file
     * @throws ClassNotFoundException If the serialized object cannot be read
     */
    public Deez load() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath + File.separator + fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (Deez) objectInputStream.readObject();
    }

    /**
     * Saves a Deez object to the storage file.
     *
     * @param deez The Deez object to save
     */
    public void save(Deez deez) {
        if (!fileExists()) {
            File dir = new File(filePath);
            dir.mkdirs();
        }
        try {
            FileOutputStream fileOutputStream =
                new FileOutputStream(filePath + File.separator + fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(deez);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeezException("Failed to save.");
        }
    }
}
