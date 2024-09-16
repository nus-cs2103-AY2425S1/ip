package blitz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import exception.BlitzException;
import exception.BlitzIoException;
import task.Task;

/**
 * Handles the reading and writing of tasks to and from a file.
 */
public class Storage {
    private String path;

    /**
     * Constructs a new Storage object with specified file path.
     *
     * @param path File path to read and store the tasks.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Writes a single task to the file (by appending).
     *
     * @param task Task object to be written to the file.
     * @throws BlitzException If I/O error occurs.
     */
    public void writeOneToFile(Task task) throws BlitzException {
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(task.getType().equals("Empty") ? "" : task.convertTaskToString());
        } catch (IOException e) {
            throw new BlitzIoException("Failed to write to database");
        }
    }

    /**
     * Writes all tasks from the provided TaskList to the file (overwrite)
     *
     * @param list TaskList that contains all the Task objects to be written to the file.
     * @throws BlitzException If I/O error occurs.
     */
    public void writeAllToFile(TaskList list) throws BlitzException {
        try (FileWriter fw = new FileWriter(path)) {
            fw.write("");
        } catch (IOException e) {
            throw new BlitzIoException("Failed to write to database");
        }

        if (!list.isEmpty()) {
            ArrayList<Task> taskList = list.getAllTask();

            assert taskList != null : "Task list from TaskList must not be null";

            for (Task task : taskList) {
                writeOneToFile(task);
            }
        }
    }

    /**
     * Reads the file and returns the file content as a TaskList object.
     *
     * @return TaskList object that containing the tasks read from the file.
     */
    public TaskList readFromFile() throws BlitzException {
        File f = new File(path);
        TaskList list = new TaskList(new ArrayList<>());

        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                list.addTask(Task.convertStringToTask(s.nextLine()));
            }
        } catch (FileNotFoundException e) {
            writeAllToFile(new TaskList(new ArrayList<>()));
        }

        return list;
    }
}
