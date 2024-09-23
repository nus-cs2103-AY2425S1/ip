package ava.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import ava.errors.Error;
import ava.errors.ErrorHandler;
import ava.task.Task;
import ava.task.TaskList;



/**
 * Manages File I/O for AVA
 */
public class FileManager {

    /**
     * Default path used to store tasks
     */
    private static final String DEFAULT_PATH = "./data/tasks.txt";
    private String path;
    private File file;
    private BufferedReader reader;
    private PrintWriter writer;
    private List<Task> taskList;
    /**
     * Creates a new FileManager with default path
     * <br>
     * Calls {@link FileManager#FileManager(String)} internally to create the FileManager
     * Only use this constructor if user doesn't want to set file path
     *
     * @see FileManager#FileManager(String)
     */
    public FileManager() {
        this(DEFAULT_PATH);
    }

    /**
     * Creates a new FileManager with given path
     * <br>
     * if no file exists at the given path one is created
     *
     * @param path Path to the file
     */
    public FileManager(String path) {
        assert path != null;
        this.path = path;
        this.file = new File(path);

        try {
            boolean isDirCreated = file.getParentFile().mkdirs();
            if (isDirCreated) {
                System.out.println("new dir created");
            }
            boolean isFileCreated = file.createNewFile();
            if (!isFileCreated) {
                System.out.println("new file created");
            }

        } catch (IOException | SecurityException e) {
            ErrorHandler.handle(Error.InvalidPath, "Invalid file path");
        }

        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            ErrorHandler.handle(Error.CorruptedData, "File corrupted or not found.");
        }

        // default Task handler
        taskList = new TaskList();
    }

    /**
     * Reads tasks from file
     * <br>
     * Reads tasks from file, parse them and
     * returns a list
     * <br>
     * used for initial loading of tasks
     *
     * @return List of tasks
     */
    public List<Task> getTasks() {
        try {
            String line = reader.readLine();
            while (line != null) {
                /*
                 * read
                 * deserialize
                 * add to taskList
                 */

                Task task;
                try {
                    task = DataManager.deserialize(line);
                } catch (IllegalArgumentException e) {
                    System.out.println("Data file is corrupted");
                    throw new IllegalArgumentException("Corrupted file");
                }
                taskList.add(task);
                line = reader.readLine();
            }

        } catch (IOException e) {
            ErrorHandler.handle(Error.CorruptedData, "File corrupted or not found.");
            throw new IllegalArgumentException("Corrupted file");
        }

        return taskList;
    }

    /**
     * Writes tasks to file
     * <br>
     * Writes tasks to file,
     * used to save tasks after each update
     *
     * @param tasks List of tasks to write
     */
    public void writeTasks(List<Task> tasks) {

        // erases the file.... as soon as writer is created...
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        } catch (IOException e) {
            ErrorHandler.handle(Error.CorruptedData, "File corrupted or not found.");
        }

        tasks.stream().map(DataManager::serialize).forEachOrdered(writer::println);
        writer.flush();
        writer.close();
    }

}
