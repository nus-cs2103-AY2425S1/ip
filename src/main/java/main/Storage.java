package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exception.CommandFoundButInvalidException;
import exception.InvalidSyntaxException;
import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDos;


/**
 * Handles the reading and writing of task data to and from a file.
 * Provides functionality to load tasks from a file and save tasks to a file
 */
public class Storage {
    private String filePath;
    private File file;

    /**
     * Constructs a {@code Storage} instance with the given filePath
     *
     * @param filePath the path to the file where data will be store. A new
     *                 file is created if the file does not exist
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
        file.getParentFile().mkdirs();

        try {
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }
    /**
     * Loads tasks from a file and return a list of tasks
     *
     * @return a {@code List} of {@code Task} objects read from the file. If the
     *         file is empty, an empty {@code List} is returned
     * @throws CommandFoundButInvalidException if the file content is corrupted and not
     *         in the required format
     */
    public List<Task> load() throws CommandFoundButInvalidException {
        List<Task> allTasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            if (!file.exists()) {
                return allTasks;
            }
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\| ");
                Task currTask;
                switch (parts[0].trim()) {
                case "T":
                    currTask = new ToDos(parts[2]);
                    break;
                case "D":
                    currTask = new Deadlines(parts[2]);
                    break;
                case "E":
                    currTask = new Events(parts[2]);
                    break;
                default:
                    throw new InvalidSyntaxException("File is corrupted");
                }
                if (Integer.parseInt(parts[1].trim()) == 1) {
                    currTask.markAsDone();
                }
                allTasks.add(currTask);
            }
        } catch (IOException e) {
            System.out.println("Error when reading file. " + e.getMessage());
        }
        return allTasks;
    }
    /**
     * Saves tasks from the provided {@code TaskList} instance to the file.
     * The file content will be overwritten with the current tasks
     *
     * @param taskList a {@code TaskList} instance containing tasks to be saved
     */
    public void put(TaskList taskList) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.file));
            bw.append(taskList.toMemoryString());
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred when saving");
        }
    }
}
