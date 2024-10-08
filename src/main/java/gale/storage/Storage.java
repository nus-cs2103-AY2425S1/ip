package gale.storage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import gale.task.Deadline;
import gale.task.Event;
import gale.task.Priority;
import gale.task.Task;
import gale.task.ToDo;

/**
 * Represents the storage that handles the loading and saving of tasks to a file.
 *
 * @author kaikquah
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a storage object with the specified file path.
     *
     * @param filePath the file path to store the tasks
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Loads the tasks from the file specified by the file path.
     *
     * @return the list of tasks loaded from the file as an ArrayList
     * @throws IOException if an I/O error occurs
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            Task task = fileStringToTask(line);
            if (task != null) {
                // task is null if it is not one of the 3 valid tasks
                taskList.add(task);
            }
        }
        reader.close();
        return taskList;
    }

    /**
     * Saves the tasks to the file specified by the file path.
     *
     * @param taskList the ArrayList of tasks to be saved
     * @throws IOException if an I/O error occurs
     */
    public void saveTasks(ArrayList<Task> taskList) throws IOException {
        assert taskList != null : "taskList should not be null, but can be empty";
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
        for (Task task : taskList) {
            writer.write(task.toFileString());
            writer.write("\n");
        }
        writer.flush();
        writer.close();
    }

    /**
     * Converts a line from the file to a Task object.
     * @param line the line from the file
     * @return the Task object created from the line in the file
     */
    public Task fileStringToTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }
        boolean isDone = parts[1].equals("1");
        Task task = constructTaskFromString(parts);
        if (isDone) {
            assert task != null;
            task.markAsDone();
        }
        return task;
    }

    private Task constructTaskFromString(String[] parts) {
        Task task;
        String type = parts[0];
        String desc = parts[2];
        Priority priority = Priority.valueOf(parts[3].toUpperCase());
        try {
            switch (type) {
            case "T":
                task = new ToDo(desc, priority);
                break;
            case "D":
                task = new Deadline(desc, parts[4], priority);
                break;
            case "E":
                task = new Event(desc, parts[4], parts[5], priority);
                break;
            default:
                return null;
            }
        } catch (DateTimeParseException e) {
            return null;
        }
        return task;
    }

}
