package tina;

import tina.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

/**
 * The <code>Storage</code> class handles reading from and writing to a file that stores the task list.
 * It provides methods to write tasks to the file and read tasks from the file.
 */
public class Storage {
    private final String filename;

    /**
     * Constructs a new <code>Storage</code> object with the specified filename.
     *
     * @param filename The name of the file where tasks will be stored.
     */
    public Storage(String filename) {
        this.filename = filename;
        File file = new File(filename);
        File directory = file.getParentFile(); // Get the parent directory
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Writes the given list of tasks to the file.
     *
     * @param list The list of tasks to be written to the file.
     * @throws TinaException if an error occurs while writing to the file.
     */
    public void write(ArrayList<Task> list) throws TinaException {
        try {
            FileWriter writer = new FileWriter(filename);
            for (Task task : list) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new TinaException("Error occurred while writing the task list to the file");
        }
    }

    /**
     * Reads the list of tasks from the file.
     *
     * @return An <code>ArrayList</code> of tasks read from the file.
     * @throws TinaException if an error occurs while reading from the file.
     */
    public ArrayList<Task> read() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Parser.parseLine(line);
                list.add(task);
            }
            reader.close();
        } catch (IOException e) {
            throw new TinaException("Error occurred while reading the task list from the file");
        }
        return list;
    }
}
