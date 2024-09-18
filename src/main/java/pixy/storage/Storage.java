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

/**
 * Stores the tasks from the TaskList to the hard disk.
 */
public class Storage {

    /** variable to store file path of the file in the hard disk */
    private final String filePath;

    /**
     * Initialises the global variable filepath with specified file path.
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
     * @throws FileNotFoundException if file is not found in the specified filePath.
     */
    public List<Task> load() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (file.exists()) {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("X");
                Task task = getTask(parts, taskType, isDone);
                assert task != null : "Failed to get task.";
                tasks.add(task);
            }
            s.close();
        }
        return tasks;
    }

    /**
     * Converts the text from file into Task objects based on Task type.
     *
     * @param parts The components of the task data split from the file line.
     * @param taskType The type of task.
     * @param isDone Whether the task is marked as done.
     * @return The corresponding Task object.
     */
    private Task getTask(String[] parts, String taskType, boolean isDone) {
        String description = parts[2];
        Task task = null;
        switch (taskType) {
        case "T":
            task = new ToDos(description);
            break;
        case "D":
            String by = parts[3];
            task = new Deadlines(description, by);
            break;
        case "E":
            String from = parts[3];
            String to = parts[4];
            task = new Event(description, from, to);
            break;
        default:
            assert false : "Unknown task type: " + taskType;
            break;
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
     * @throws IOException if there is an I/O error.
     */
    public void save(List<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        FileWriter fw = new FileWriter(filePath);
        assert tasks != null : "Task list cannot be null";
        for (Task task : tasks) {
            assert task != null : "Null task encountered while saving";
            fw.write(taskToFileFormat(task)
                    + System.lineSeparator());
        }
        fw.flush();
        fw.close();
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
            return "T | " + task.getStatusIcon() + " | "
                    + task.getDescription();
        } else if (task instanceof Deadlines) {
            Deadlines deadline = (Deadlines) task;
            return "D | " + deadline.getStatusIcon() + " | " + deadline.getDescription()
                    + " | " + deadline.getDueDateTime();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + event.getStatusIcon() + " | " + event.getDescription()
                    + " | " + event.getFrom() + " | " + event.getTo();
        }
        assert false : "Unknown task type in taskToFileFormat";
        return "";
    }
}
