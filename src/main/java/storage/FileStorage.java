package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A concrete implementation of the Storage class that manages data using a file system.
 * It reads from and writes to a file in a specified directory.
 */
public class FileStorage extends Storage {
    /** The directory path where the storage file is located. */
    protected String dirPath;

    /** The file where the data is stored. */
    protected File file;

    /**
     * Constructs a FileStorage object and initializes the storage file.
     * If the directory or file does not exist, they will be created.
     *
     * @param dirPath The directory path where the storage file is located.
     */
    public FileStorage(String dirPath) {
        // Check if dirPath is null
        assert dirPath != null : "dirPath argument cannot be null";

        this.dirPath = dirPath;

        // Creates all folders if they do not exist
        try {
            Files.createDirectories(Paths.get(dirPath));
        } catch (IOException e) {
            // Handle error (can log this if needed)
            e.printStackTrace();
        }

        // Creates file if it does not exist
        file = new File(this.dirPath + "/storage.txt");

        try {
            file.createNewFile();
        } catch (IOException ioException) {
            System.out.println("Unable to create task save file at path " + file.getAbsolutePath()
                    + "\n" + ioException.getMessage());
        }
    }

    /**
     * Loads all the data from the storage file.
     *
     * @return A list of strings representing the data loaded from the file.
     */
    @Override
    public List<String> load() {
        try (Scanner fs = new Scanner(file)) {
            List<String> res = new ArrayList<>();

            while (fs.hasNextLine()) {
                String line = fs.nextLine();
                res.add(line);
            }

            return res;
        } catch (FileNotFoundException e) {
            System.out.println("Unable to load data in storage:\n" + e.getMessage());

            return new ArrayList<>();
        }
    }

    /**
     * Stores the given element by appending it to the storage file.
     *
     * @param elem The element to be stored.
     * @return A list of strings representing the updated data in the file.
     */
    @Override
    public List<String> store(String elem) {
        try {
            // Append to previous save
            FileWriter fw = new FileWriter(file, true);
            fw.write(elem + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to add element:\n" + e.getMessage());
        }

        return load();
    }

    /**
     * Overwrites the existing data in the storage file with the provided data.
     *
     * @param data The new data to overwrite the existing data in the storage file.
     * @return A list of strings representing the updated data in the file.
     */
    @Override
    public List<String> update(List<String> data) {
        try {
            // Overwrite previous save
            FileWriter fw = new FileWriter(file);

            for (String line : data) {
                fw.write(line + "\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to update data:\n" + e.getMessage());
        }

        return load();
    }
}
