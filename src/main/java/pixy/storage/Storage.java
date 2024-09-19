package pixy.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pixy.tasks.Deadlines;
import pixy.tasks.Event;
import pixy.tasks.Task;
import pixy.tasks.ToDos;
import pixy.PixyExceptions;

/**
 * Stores the tasks from the TaskList to the hard disk.
 */
public class Storage {

    /** Variable to store file path of the file in the hard disk */
    private final String filePath;

    /**
     * Initializes the global variable filepath with the specified file path.
     *
     * @param filePath The file path to store or load the tasks.
     */
    public Storage(String filePath) {
        assert filePath != null : "File Path should not be null";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file whenever the chatbot is started.
     *
     * @return List of tasks loaded from the file.
     * @throws PixyExceptions if file is not found, access is denied, or file content is corrupted.
     */
    public List<Task> load() throws PixyExceptions {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            throw new PixyExceptions("File not found: " + filePath + ". Starting with an empty task list.");
        }

        if (!file.canRead()) {
            throw new PixyExceptions("Access denied: Unable to read the file at " + filePath);
        }

        try (Scanner s = new Scanner(file)) {
            while (s.hasNext()) {
                String line = s.nextLine();
                try {
                    tasks.add(parseLine(line));
                } catch (PixyExceptions | ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                    System.err.println("Warning: Skipping corrupted task entry in file: " + line);
                    // You can choose to log this error or handle it accordingly
                }
            }
        } catch (FileNotFoundException e) {
            throw new PixyExceptions("File not found despite existence check: " + filePath);
        }
        return tasks;
    }

    /**
     * Parses a single line from the file and converts it to a Task object.
     *
     * @param line The line of text representing a task.
     * @return The corresponding Task object.
     * @throws PixyExceptions if the line is not in the expected format.
     */
    private Task parseLine(String line) throws PixyExceptions {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new PixyExceptions("Corrupted file format. Line does not have sufficient data: " + line);
        }

        String taskType = parts[0];
        boolean isDone = parts[1].equals("X");
        Task task = getTask(parts, taskType, isDone);
        assert task != null : "Failed to parse task.";
        return task;
    }

    /**
     * Converts the text from file into Task objects based on Task type.
     *
     * @param parts The components of the task data split from the file line.
     * @param taskType The type of task.
     * @param isDone Whether the task is marked as done.
     * @return The corresponding Task object.
     */
    private Task getTask(String[] parts, String taskType, boolean isDone) throws PixyExceptions {
        String description = parts[2];
        Task task = null;

        switch (taskType) {
        case "T":
            task = new ToDos(description);
            break;
        case "D":
            if (parts.length < 4) {
                throw new PixyExceptions("Corrupted deadline entry: missing 'by' field.");
            }
            String by = parts[3];
            task = new Deadlines(description, by);
            break;
        case "E":
            if (parts.length < 5) {
                throw new PixyExceptions("Corrupted event entry: missing 'from' or 'to' field.");
            }
            String from = parts[3];
            String to = parts[4];
            task = new Event(description, from, to);
            break;
        default:
            throw new PixyExceptions("Unknown task type: " + taskType);
        }

        if (task != null && isDone) {
            task.markAsDone(true);
        }
        return task;
    }

    /**
     * Saves the current list of tasks into the file.
     *
     * @param tasks the list of tasks to be saved to the file
     * @throws PixyExceptions if there is an I/O error or file access issue.
     */
    public void save(List<Task> tasks) throws PixyExceptions {
        assert tasks != null : "Task list cannot be null";

        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create directories if they don't exist
                file.createNewFile(); // Create the file if it doesn't exist
            }

            if (!file.canWrite()) {
                throw new PixyExceptions("Access denied: Unable to write to file at " + filePath);
            }

            try (FileWriter fw = new FileWriter(file)) {
                for (Task task : tasks) {
                    assert task != null : "Null task encountered while saving";
                    fw.write(taskToFileFormat(task) + System.lineSeparator());
                }
                fw.flush();
            }

        } catch (IOException e) {
            throw new PixyExceptions("Error writing to file: " + filePath);
        }
    }

    /**
     * Converts the Task object into a string matching the format of the File.
     *
     * @param task The Task object to be converted into String.
     * @return A string representation of the task in the desired file format.
     */
    public String taskToFileFormat(Task task) {
        assert task != null : "Null task passed to taskToFileFormat";
        if (task instanceof ToDos) {
            return "T | " + task.getStatusIcon() + " | " + task.getDescription();
        } else if (task instanceof Deadlines) {
            Deadlines deadline = (Deadlines) task;
            return "D | " + deadline.getStatusIcon() + " | " + deadline.getDescription() + " | " +
                    deadline.getDueDateTime();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + event.getStatusIcon() + " | " + event.getDescription() + " | " + event.getFrom() +
                    " | " + event.getTo();
        }
        assert false : "Unknown task type in taskToFileFormat";
        return "";
    }
}
