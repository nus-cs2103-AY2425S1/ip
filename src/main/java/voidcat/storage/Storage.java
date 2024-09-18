package voidcat.storage;

import voidcat.task.*;
import voidcat.exception.VoidCatException;
import voidcat.ui.Ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the storage where tasks are saved and loaded from
 * in the Void Cat program.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage initialized with the path to the file.
     *
     * @param filePath The path to the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * If file exists, loads the tasks saved in the file previously.
     * Else, calls ensureFileAndDirectoryExist()
     *
     * @return The list of tasks from the file.
     * @throws IOException If file cannot be read.
     * @throws VoidCatException If file or directory cannot be created or no tasks found.
     * @throws SecurityException If read access to the file is denied
     * when checking if file exists.
     */
    public ArrayList<Task> load() throws IOException, VoidCatException, SecurityException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            ensureFileAndDirectoryExist(file);
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    /**
     * Saves tasks from the task list.
     *
     * @param tasks The list of tasks to be added.
     */
    public void save(TaskList tasks) throws IOException, VoidCatException {
        ensureFileAndDirectoryExist(new File(filePath));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            tasks.saveTasks(bw);
        }
    }

    /**
     * Checks if file and directory exists, else creates them.
     *
     * @param file The file to check for saved tasks.
     * @throws VoidCatException If file or directory cannot be created.
     * @throws SecurityException If read access to the directory is denied
     * when checking if directory exists.
     * @throws IOException If an I/O error occurs.
     */
    private void ensureFileAndDirectoryExist(File file) throws VoidCatException, SecurityException, IOException {
        File directory = new File(file.getParent());
        if (!directory.exists()) {
            boolean isDirectoryMade = directory.mkdirs();
            assert isDirectoryMade : "Error in creating directory!";
            if (!isDirectoryMade) {
                throw new VoidCatException("Error in creating directory!");
            }
        }
        if (!file.exists()) {
            boolean isFileMade = file.createNewFile();
            assert isFileMade : "Error in creating file!";
            if (!isFileMade) {
                throw new VoidCatException("Error in creating file!");
            }
        }
    }

    /**
     * Parses the string formatted task line and constructs the Task instance
     *
     * @param line The string formatted task line.
     * @return The task instance.
     */
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
        case "T":
            return new ToDo(parts[2], Integer.parseInt(parts[1]));
        case "D":
            return new Deadline(parts[2], parts[3], Integer.parseInt(parts[1]));
        case "E":
            return new Event(parts[2], parts[3], parts[4], Integer.parseInt(parts[1]));
        default:
            return null;
        }
    }
}