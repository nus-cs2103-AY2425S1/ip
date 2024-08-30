package sage.storage;

import sage.exception.SageException;
import sage.task.*;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage class handles the loading and saving of tasks to a file
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor to initialise the storage with the given file path
      */
    public Storage(String filepath) {
        this.filePath = filepath;
    }

    /**
     * Loads tasks from the file at the specified file path
     *
     * @return Lists of tasks loaded from the file
     * @throws SageException If there is an error loading the tasks
     */
    public List<Task> load() throws SageException {
        List<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new SageException("Error loading tasks from file");
        }
        return tasks;
    }

    /**
     * Save the lists of tasks to the file at the specified file path
     *
     * @param tasks TaskList containing the tasks to be saved
     * @throws SageException If there is an error saving the tasks
     */
    public void saveTasks(TaskList tasks) throws SageException{
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks.getAllTasks()) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new SageException("Error writing to file");
        }
    }

    /**
     * Parses a line of text and converts it into a task object
     *
     * @param line The line of text representing the task
     * @return The Task object represented by the input line
     * @throws SageException If the task type is invalid or if the line format is incorrect
     */
    private Task parseTask(String line) throws SageException {
        String parts[] = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch(taskType) {
            case "T":
                Task todo = new ToDo(description);
                if (isDone) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                String by = parts[3];
                Task deadline = new Deadline(description, by);
                if (isDone) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                String from = parts[3];
                String to = parts[4];
                Task event = new Event(description, from, to);
                if (isDone) {
                    event.markAsDone();
                }
                return event;
            default:
                throw new SageException("Invalid task type");
        }
    }
}
