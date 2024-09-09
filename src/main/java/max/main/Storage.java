package max.main;

import max.exception.MaxException;
import max.task.Deadline;
import max.task.Event;
import max.task.Task;
import max.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The Storage class is responsible for reading from and writing to the file system.
 * It loads the task list from a file and saves the current task list back to the file.
 */
public class Storage {
    private String path;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param path The file path where tasks are stored.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads the task list from the file specified in the path.
     * If the file does not exist, it creates a new file and returns an empty task list.
     *
     * @return An ArrayList of tasks loaded from the file.
     */
    public ArrayList<Task> loadList() {
        ArrayList<Task> tempList = new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.exists()) {
                new File(file.getParent()).mkdirs();
                file.createNewFile();
                return tempList;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = createTask(line);
                tempList.add(task);
                assert tempList.size() <= 100 : "Task list exceeds the maximum size of 100.";
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An IO Exception has occured. " + e.getMessage());
        } catch (MaxException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tempList;

    }

    /**
     * Creates a Task object from a line read from the file.
     * The line format is dependent on the task type, and tags, if present, are handled accordingly.
     *
     * @param line A string representing the task in the file format.
     * @return The Task object created from the string.
     * @throws MaxException If an unknown task type is found or if any other error occurs during task creation.
     */
    private static Task createTask(String line) throws MaxException {
        String[] parts = line.split(" \\| ");
        Task task;
        boolean isTagged;

        switch (parts[0]) {
        case "T":
            task = new Todo(parts[2]);

            isTagged = parts.length > 3;

            break;
        case "D":
            Parser tempParser = new Parser();
            LocalDateTime LDT = tempParser.parseDate(parts[3]);
            if (LDT != null) {
                task = new Deadline(parts[2], LDT);
            } else {
                task = new Deadline(parts[2], parts[3]);
            }

            isTagged = parts.length > 4;

            break;
        case "E":
            task = new Event(parts[2], parts[3]);

            isTagged = parts.length > 4;

            break;
        default:
            throw new MaxException("Unknown task type found in file.");
        }

        if (parts[1].equals("1")) {
            task.markDone();
        }

        if (isTagged) {
            // Tags are assumed to be the last part if there are extra parts
            String[] tags = parts[parts.length - 1].split(",");
            for (String tag : tags) {
                task.addTag(tag.trim());
            }
        }

        return task;
    }

    /**
     * Saves the current list of tasks to the file specified in the path.
     *
     * @param storedTasks The list of tasks to be saved to the file.
     * @throws MaxException If an IOException occurs during the save process.
     */
    public void saveTasks(ArrayList<Task> storedTasks) throws MaxException {
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();

            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            for (Task task : storedTasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            throw new MaxException("An IOException occurred.");
        }
    }

}
