package orion.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import orion.exceptions.OrionException;
import orion.exceptions.OrionInputException;
import orion.exceptions.OrionPathNotFoundException;
import orion.exceptions.OrionSaveTaskListException;
import orion.exceptions.OrionTaskDataException;
import orion.tasks.Deadline;
import orion.tasks.Event;
import orion.tasks.Task;
import orion.tasks.Todo;

/**
 * Handles the loading and saving of tasks to and from a file.
 * The tasks are stored in a text file, where each line represents a task.
 */
public class Storage {

    /**
     * The pathname of the file where tasks are saved.
     */
    public static final String DATA_PATHNAME = "./data/tasks.txt";

    /**
     * The pathname of the folder where the data file is stored.
     */
    public static final String DATA_FOLDER_PATHNAME = "./data";

    /**
     * Constructs a new {@code Storage} object.
     */
    public Storage() {

    }

    /**
     * Loads the tasks from the file specified by {@link #DATA_PATHNAME}.
     * If the file does not exist or is corrupted, a new file is created.
     *
     * @return A list of tasks loaded from the file.
     * @throws OrionException If there are issues with the file's format or
     *                        if the file cannot be found or created.
     */
    public List<Task> loadTasks() throws OrionException {
        File taskList = new File(DATA_PATHNAME);
        Path path = Paths.get(DATA_PATHNAME);
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(taskList);

            while (s.hasNext()) {
                String task = s.nextLine();
                String[] parsed = task.split(",");
                switch (parsed[0]) {
                case "todo":
                    tasks.add(parseTodo(parsed));
                    break;
                case "deadline":
                    tasks.add(parseDeadline(parsed));
                    break;
                case "event":
                    tasks.add(parseEvent(parsed));
                    break;
                default:
                    // File corrupted
                    throw new OrionTaskDataException("Unrecognised task type");
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            createTaskListIfNotFound(path);

            // Will never be thrown, since createTaskListIfNotFound always throws an exception
            throw new OrionInputException("Unexpected error when creating save list!");
        } catch (OrionTaskDataException | DateTimeException e) {
            createTaskListIfCorrupted(path);

            // Will never be thrown, since createTaskListIfCorrupted always throws an exception
            throw new OrionInputException("Unexpected error when creating save list!");
        }
    }

    /**
     * Saves the provided list of task descriptions to the file specified by {@link #DATA_PATHNAME}.
     *
     * @param saveTaskDescriptions A list of task descriptions to be saved.
     * @throws OrionSaveTaskListException If the tasks cannot be saved to the file.
     */
    public void saveTasks(List<String> saveTaskDescriptions) throws OrionSaveTaskListException {
        assert saveTaskDescriptions != null : "saveTaskDescriptions cannot be null";
        try {
            FileWriter fw = new FileWriter(DATA_PATHNAME);
            for (String s : saveTaskDescriptions) {
                fw.write(s);
            }
            fw.close();
        } catch (IOException e) {
            throw new OrionSaveTaskListException("Task list cannot be saved");
        }
    }

    /**
     * Parses a {@link Todo} task from a string array.
     *
     * @param taskString The string array representing the task data.
     * @return A {@link Todo} object created from the provided data.
     * @throws OrionTaskDataException If the data format is invalid.
     */
    private Todo parseTodo(String[] taskString) throws OrionTaskDataException {
        if (taskString.length != 3) {
            throw new OrionTaskDataException("Unrecognised todo task format");
        } else {
            boolean done = taskString[1].equals("T");
            return new Todo(taskString[2], done);
        }
    }

    /**
     * Parses a {@link Deadline} task from a string array.
     *
     * @param taskString The string array representing the task data.
     * @return A {@link Deadline} object created from the provided data.
     * @throws OrionTaskDataException If the data format is invalid.
     */
    private Deadline parseDeadline(String[] taskString) throws OrionTaskDataException {
        if (taskString.length != 4) {
            throw new OrionTaskDataException("Unrecognised deadline task format");
        } else {
            boolean done = taskString[1].equals("T");
            LocalDate time = LocalDate.parse(taskString[3]);
            return new Deadline(taskString[2], done, time);
        }
    }

    /**
     * Parses an {@link Event} task from a string array.
     *
     * @param taskString The string array representing the task data.
     * @return An {@link Event} object created from the provided data.
     * @throws OrionTaskDataException If the data format is invalid.
     */
    private Event parseEvent(String[] taskString) throws OrionTaskDataException {
        if (taskString.length != 5) {
            throw new OrionTaskDataException("Unrecognised event task format");
        } else {
            boolean done = taskString[1].equals("T");
            LocalDate start = LocalDate.parse(taskString[3]);
            LocalDate end = LocalDate.parse(taskString[4]);
            return new Event(taskString[2], done, start, end);
        }
    }

    /**
     * Creates a new task list file if the file is not found.
     * <p>
     * This method creates the necessary directories and the task list file if they do not already exist.
     * If the file is successfully created, an exception is thrown to signal that the list was not found
     * and a new one has been created.
     * </p>
     *
     * @param path The path of the task list file.
     * @throws OrionTaskDataException If the task list file was not found and a new list is created.
     * @throws OrionPathNotFoundException If there is an issue creating the necessary directories or file.
     */
    private void createTaskListIfNotFound(Path path)
            throws OrionTaskDataException, OrionPathNotFoundException {
        try {
            File dataDirectory = new File(DATA_FOLDER_PATHNAME);
            Path dir = Paths.get(DATA_FOLDER_PATHNAME);
            if (dataDirectory.exists() && dataDirectory.isDirectory()) {
                Files.createFile(path);
            } else if (dataDirectory.exists()) {
                Files.delete(dir);
                Files.createDirectory(dir);
                Files.createFile(path);
            } else {
                Files.createDirectory(dir);
                Files.createFile(path);
            }
            throw new OrionTaskDataException("Task list not found, new list created");
        } catch (IOException err) {
            throw new OrionPathNotFoundException("Task list cannot be created");
        }
    }

    /**
     * Recreates the task list file if the current file is corrupted.
     * <p>
     * This method deletes the corrupted file and creates a new empty task list file.
     * If the file is successfully recreated, an exception is thrown to signal that the previous
     * list was corrupted and a new one has been created.
     * </p>
     *
     * @param path The path of the corrupted task list file.
     * @throws OrionTaskDataException If the task list file was corrupted and a new list is created.
     * @throws OrionPathNotFoundException If there is an issue deleting or recreating the file.
     */
    private void createTaskListIfCorrupted(Path path)
            throws OrionTaskDataException, OrionPathNotFoundException {
        try {
            Files.delete(path);
            Files.createFile(path);
            throw new OrionTaskDataException("Task list corrupted, new list created");
        } catch (IOException err) {
            throw new OrionPathNotFoundException("Task list cannot be created");
        }
    }
}
