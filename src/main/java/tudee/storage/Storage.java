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
    private static final int COMMAND_INDEX = 0;
    private static final int MARK_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    private static final int DEADLINE_INDEX = 3;
    private static final int START_INDEX = 3;
    private static final int END_INDEX = 4;
    private static final int MINIMUM = 3;
    private static final int DEADLINE_SEGMENTS = 4;
    private static final int EVENT_SEGMENTS = 5;

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
                assert data.length >= MINIMUM : "Format is incorrect. You should have at least 3 segments.";

                Task currentTask = createTaskFromData(data);
                checkMark(data, currentTask);
                tasks.add(currentTask);
            }
        } catch (IOException | TudeeException e) {
            System.out.println("Error in loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Checks what command the first element of the array contains.
     *
     * @return a new task corresponding to the command.
     * @param data A string array consisting of task description and other requirements depending on task type.
     */
    private Task createTaskFromData(String[] data) throws TudeeException {
        switch (data[COMMAND_INDEX]) {
            case "T":
                return new ToDo(data[DESCRIPTION_INDEX]);
            case "D":
                // Assert that Deadline has 4 segments.
                assert data.length == DEADLINE_SEGMENTS: "Deadline must have 4 segments";
                return new Deadline(data[DESCRIPTION_INDEX], data[DEADLINE_INDEX]);
            case "E":
                // Assert that Events has 5 segments.
                assert data.length == EVENT_SEGMENTS: "Events must have 5 segments";
                return new Events(data[DESCRIPTION_INDEX], data[START_INDEX], data[END_INDEX]);
            default:
                throw new TudeeException("Invalid letter");
        }
    }

    /**
     * Checks whether the current task was marked or not.
     *
     * @param data A string array consisting of task description and other requirements depending on task type.
     * @param task The current task we are checking.
     */
    private void checkMark(String[] data, Task task) throws TudeeException {
        if (data[MARK_INDEX].equals("1")) {
            task.markAsDone();
        }
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
