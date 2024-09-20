package johncena.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import johncena.exceptions.CenaException;
import johncena.tasks.Deadline;
import johncena.tasks.Event;
import johncena.tasks.Task;
import johncena.tasks.Todo;



/**
 * This class is responsible for saving and loading tasks from a file.
 */
public class Storage {
    public static final String SEPARATOR = " | ";
    private static String filePath = "./data/CenaTaskList.txt"; // Default file path

    /**
     * Sets the file path for saving and loading tasks.
     *
     * @param path The file path to set.
     */
    public static void setFilePath(String path) {
        assert path != null : "File path should not be null";
        filePath = path;
    }


    /**
     * Saves the list of tasks to a file.
     *
     * @param tasks The list of tasks to save.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks list should not be null";
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(toSaveString(task) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    /**
     * Loads the list of tasks from a file.
     *
     * @return The list of tasks loaded from the file.
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);

            // Check if directory or file do not exist
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    tasks.add(parseTask(line));
                } catch (CenaException e) {
                    System.out.println(e.getMessage());
                }
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return tasks;
    }


    /**
     * Parses a line from the file into a tasks.Task object.
     *
     * @param line The line to parse.
     * @return The tasks.Task object created from the line.
     * @throws CenaException If the line cannot be parsed into a tasks.Task object.
     */
    private static Task parseTask(String line) throws CenaException {
        assert line != null : "Line to parse should not be null";
        Task task = null;
        String[] taskDescription = line.trim().split("\\s*\\|\\s*");
        String taskType = taskDescription[0];

        switch (taskType) {
        case "T":
            task = new Todo(taskDescription[2]);
            break;

        case "D":
            task = new Deadline(taskDescription[2], taskDescription[3]);
            break;

        case "E":
            String[] eventTimes = taskDescription[3].split(" ~ ");
            task = new Event(taskDescription[2], eventTimes[0], eventTimes[1]);
            break;


        default:
            System.out.println("Unknown task type: " + taskType);
            break;
        }

        boolean isDone = taskDescription[1].equals("1");
        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Converts a tasks.Task object into a string that can be saved to a file.
     *
     * @param task The tasks.Task object to convert.
     * @return The string representation of the tasks.Task object.
     */
    private static String toSaveString(Task task) {
        assert task != null : "Task to save should not be null";
        String taskStatus = task.isTaskDone() ? "1" : "0";
        String taskDescription = "";

        if (task instanceof Todo todo) {
            taskDescription = "T" + SEPARATOR + taskStatus + SEPARATOR + todo.getDescription();
        } else if (task instanceof Deadline deadline) {
            taskDescription = "D" + SEPARATOR + taskStatus + SEPARATOR + deadline.getDescription() + SEPARATOR
                    + deadline.getBy();
        } else if (task instanceof Event event) {
            taskDescription = "E" + SEPARATOR + taskStatus + SEPARATOR + event.getDescription() + SEPARATOR
                    + event.getFrom() + " ~ " + event.getTo();
        }

        return taskDescription;
    }
}
