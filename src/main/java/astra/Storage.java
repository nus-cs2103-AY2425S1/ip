package astra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import astra.task.Task;

/**
 * Storage class to handle loading and saving of tasks.
 */
public class Storage {
    private static final String FILENAME = "/tasks.txt";
    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads tasks from the data file.
     *
     * @return List of tasks loaded from the data file.
     * @throws AstraException If the data file is corrupted.
     */
    public ArrayList<Task> load() throws AstraException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(path + FILENAME);
        try {
            Scanner inp = new Scanner(f);
            while (inp.hasNextLine()) {
                String line = inp.nextLine();
                tasks.add(Task.fromText(line));
            }
            inp.close();
        } catch (FileNotFoundException e) {
            return tasks;
        } catch (Exception e) {
            throw new AstraException("Data file corrupted, failed to read all tasks.");
        }

        return tasks;
    }

    /**
     * Saves tasks to the data file.
     *
     * @param tasks List of tasks to be saved.
     */
    public void save(TaskList tasks) {
        String text = tasks.toTextFile();

        try {
            // Create directory if it does not exist
            File dir = new File(path);
            dir.mkdirs();
            assert dir.exists() : "Directory should exist";
            FileWriter fw = new FileWriter(path + FILENAME);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
