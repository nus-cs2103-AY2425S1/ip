package bao.main;

import bao.task.Task;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * The Storage class reads from and writes to the storage file which contains the list of tasks.
 * It provides methods to load and save tasks from a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path to store tasks.
     *
     * @param filePath Path of the file used for storing tasks.
     */
    public Storage (String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path, if file does not exist, it returns an empty list of tasks.
     *
     * @return ArrayList of Task objects.
     * @throws IOException If an I/O error occurs while reading from the file.
     * @throws FileNotFoundException If the file cannot be found.
     */
    public ArrayList<Task> loadFile() throws IOException, FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            Task task = Task.fromString(line);
            tasks.add(task);
        }
        reader.close();
        return tasks;
    }

    /**
     * Saves the list of tasks to the file specified by the file path, if file does not exist, it creates a new file.
     *
     * @param tasks ArrayList of Task objects.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        PrintWriter writer = new PrintWriter(file);
        for (Task task : tasks) {
            writer.println(task.toFileString());
        }
        writer.close();
    }
}
