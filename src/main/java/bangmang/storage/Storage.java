package bangmang.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import bangmang.exception.InvalidCommandException;
import bangmang.exception.InvalidTaskFormatException;
import bangmang.tasks.Task;

/**
 * The Storage class handles the loading and saving of task data from and to the hard drive.
 * It manages reading tasks from a file, creating task objects, and saving tasks back to the file.
 */

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task data from the hard drive and returns an array list of tasks.
     *
     * @return ArrayList of Task objects.
     * @throws InvalidCommandException if there is an error reading the file.
     * @throws InvalidTaskFormatException if the task format is invalid.
     */
    public ArrayList<Task> load() throws InvalidCommandException {
        ArrayList<Task> list = new ArrayList<>();
        File dataFile = new File(filePath);
        if (!dataFile.getParentFile().exists()) {
            dataFile.getParentFile().mkdirs();
        }
        if (dataFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        Task task = Task.readSavedTask(line);
                        list.add(task);
                    } catch (InvalidTaskFormatException e) {
                        System.out.println("Alamak, warning: Skipping invalid task format: " + line);
                    }
                }
            } catch (IOException e) {
                throw new InvalidCommandException("Alamak, error reading the file: " + e.getMessage());
            }
        }
        return list;
    }

    /**
     * Saves task data to the hard drive.
     *
     * @param list The list of tasks to save.
     * @throws InvalidCommandException if there is an error writing to the file.
     * @throws InvalidTaskFormatException if a task cannot be saved.
     */
    public void save(ArrayList<Task> list) throws InvalidCommandException {
        File dataFile = new File(filePath);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile))) {
            for (Task task : list) {
                try {
                    bw.write(task.writeSavedTask());
                    bw.newLine();
                } catch (InvalidTaskFormatException e) {
                    System.out.println("Alamak, warning: Cannot write task: " + task.toString());
                }
            }
        } catch (IOException e) {
            throw new InvalidCommandException("Alamak, error writing to the file: " + e.getMessage());
        }
    }
}
