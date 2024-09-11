package tudee.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tudee.TudeeException;
import tudee.task.Deadline;
import tudee.task.Events;
import tudee.task.Task;
import tudee.task.ToDo;

/**
 * Handles loading and saving of tasks to and from a file.
 * This class provides methods to read tasks from a file and write tasks to a file.
 */
public class Storage {
    private final String path;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param path the path to the file where tasks are stored.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads the tasks from the file specified by the path.
     * Tasks are read from the specified file and parsed into appropriate Task objects.
     * If the file does not exist, an empty list is returned.
     *
     * @return a list of tasks loaded from the file.
     * @throws TudeeException if there is an error in processing the task list.
     */
    public List<Task> load() throws TudeeException {
        List<Task> tasks = new ArrayList<>();
        File currentFile = new File(path);
        if (!currentFile.exists()) {
            return tasks;
        }

        try (Scanner sc = new Scanner(currentFile)) {
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                String[] data = currentLine.split(" \\| ");

                // Assert that the data array has the expected length.
                assert data.length >= 3 : "Format is incorrect. You should have at least 3 segments.";
                Task currentTask;
                try {
                    switch (data[0]) {
                    case "T":
                        currentTask = new ToDo(data[2]);
                        break;
                    case "D":
                        // Assert that Deadline has 4 segments.
                        assert data.length == 4: "Deadline must have 4 segments";
                        currentTask = new Deadline(data[2], data[3]);
                        break;
                    case "E":
                        // Assert that Events has 5 segments.
                        assert data.length == 5: "Events must have 5 segments";
                        currentTask = new Events(data[2], data[3], data[4]);
                        break;
                    default:
                        throw new TudeeException("Invalid letter");
                    }
                    if (data[1].equals("1")) {
                        currentTask.markAsDone();
                    }
                    tasks.add(currentTask);
                } catch (TudeeException e) {
                    System.out.println("Error procesesing task list: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error in loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the file specified by the path.
     * Each task is written to the file in a standardised format.
     *
     * @param tasks the list of tasks to be saved.
     */
    public void save(List<Task> tasks) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            for (Task currentTask : tasks) {
                pw.println(currentTask.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Failed to save tasks: " + e.getMessage());
        }
    }
}
