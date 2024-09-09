package taskon.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import taskon.exception.TaskonException;
import taskon.task.Deadline;
import taskon.task.Event;
import taskon.task.Task;
import taskon.task.TaskList;
import taskon.task.Todo;

/**
 * Handles the loading and saving of tasks to and from a storage file.
 */
public class Storage {
    private static final String FILE_PATH = "./data/taskon.txt";
    private static final String SEPARATOR = "|";
    private final String path;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where the tasks are stored.
     */
    public Storage(String filePath) {
        this.path = filePath;
        assert filePath != null : "File path should not be null";
    }

    /**
     * Saves the list of tasks to the storage file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     */
    public void saveTasks(TaskList tasks) {
        assert tasks != null : "TaskList should not be null";

        try {
            FileWriter fw = new FileWriter(this.path);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.getTask(i);
                assert task != null : "Task should not be null";
                fw.write(taskToFileString(task) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Loads the list of tasks from the storage file.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws TaskonException If the file cannot be loaded or parsed.
     */
    public ArrayList<Task> load() throws TaskonException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(this.path);

            // check if directory or file do not exist
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tasks.add(parseTask(line));
            }
            scanner.close();

        } catch (FileNotFoundException | TaskonException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return tasks;
    }


    /**
     * Converts a line from the storage file into a Task object.
     *
     * @param line The line from the file representing a task.
     * @return The Task object created from the line.
     * @throws TaskonException If the line cannot be parsed into a valid Task.
     */
    private static Task parseTask(String line) throws TaskonException {
        Task task = null;
        String[] taskDescription = line.trim().split("\\s*\\" + SEPARATOR + "\\s*");
        String taskType = taskDescription[0];

        switch (taskType) {
        case "T":
            task = new Todo(taskDescription[2]);
            break;

        case "D":
            task = new Deadline(taskDescription[2], taskDescription[3]);
            break;

        case "E":
            task = new Event(taskDescription[2], taskDescription[3], taskDescription[4]);
            break;

        default:
            System.out.println("Unknown task type: " + taskType);
            break;
        }

        boolean isDone = taskDescription[1].equals("1");
        if (task != null && isDone) {
            try {
                task.markAsDone();
            } catch (TaskonException e) {
                System.out.println(e.getMessage());
            }

        }

        return task;
    }

    /**
     * Converts a Task object into a formatted string suitable for saving to the file.
     *
     * @param task The Task object to convert.
     * @return A formatted string representing the Task.
     */
    private static String taskToFileString(Task task) {
        assert task != null : "Task should not be null";

        String taskStatus = task.getIsDone() ? "1" : "0";

        String taskDescription;
        if (task instanceof Todo) {
            taskDescription = "T" + SEPARATOR + taskStatus + SEPARATOR + task.getDescription();
        } else if (task instanceof Deadline) {
            taskDescription = "D" + SEPARATOR + taskStatus + SEPARATOR + task.getDescription()
                    + SEPARATOR
                    + ((Deadline) task).getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else {
            taskDescription = "E" + SEPARATOR + taskStatus + SEPARATOR + task.getDescription()
                    + SEPARATOR + ((Event) task).getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                    + SEPARATOR + ((Event) task).getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

        return taskDescription;
    }
}
