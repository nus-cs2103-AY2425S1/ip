package mummy.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Storage class is responsible for loading and saving data to a file.
 * It provides methods to load data from a file into an ArrayList of strings,
 * and to save a collection of strings to a file.
 */
public class Storage {
    private final String ioPath;

    /**
     * Constructs a new Storage object with the specified input/output path.
     *
     * @param ioPath the input/output path for the storage
     */
    public Storage(String ioPath) {
        this.ioPath = ioPath;
    }

    /**
     * Loads data from the specified file.
     * @return An ArrayList of strings containing the lines of the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public List<String> load() throws IOException {
        File file = new File(ioPath);
        List<String> lines = new ArrayList<>();

        if (file.exists()) {
            // if file already exists or is successfully created
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                lines.add(sc.nextLine());
            }

            // clean up
            sc.close();
        } else {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        return lines;
    }

    /**
     * Saves the collection of lines to the file specified by the current IO path.
     * @param lines the collection of lines to be saved
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void save(List<String> lines) throws IOException {
        assert (new File(ioPath)).exists();

        FileWriter fw = new FileWriter(this.ioPath, false);
        for (String line : lines) {
            fw.write(line + "\n");
        }
        fw.close();
    }
}
