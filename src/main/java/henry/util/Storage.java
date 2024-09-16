package henry.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import henry.HenryException;
import henry.task.Deadline;
import henry.task.Event;
import henry.task.Task;
import henry.task.Todo;

/**
 * Deals with loading tasks from the file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the path of the file.
     *
     * @return The path of the file.
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Returns an ArrayList of tasks recorded in the hard disk.
     *
     * @return An ArrayList of tasks that are in the form of
     *                 event, deadline and todo.
     * @throws HenryException If there is an issue accessing the file, creating
     *                 the directory, or reading the tasks.
     */
    public ArrayList<Task> load() throws HenryException {
        ArrayList<Task> recordedTasks = new ArrayList<>();

        File file = new File(this.filePath);
        ensureFileExists(file);

        //Check if the file is empty
        if (file.length() == 0) {
            return recordedTasks;
        }

        //Read and parse the tasks
        readTasksFromFile(file, recordedTasks);
        return recordedTasks;
    }

    /**
     * Ensures that the file and its parent directories exist. If they do not exist,
     * this method creates them.
     *
     * @param file The file to check and possibly create.
     * @throws HenryException If there is an error creating the file or directory.
     */
    private void ensureFileExists(File file) throws HenryException {
        try {
            // Ensure directory exists
            if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                throw new HenryException("Failed to create directory for file path");
            }
            // Ensure file exists
            if (!file.exists() && !file.createNewFile()) {
                throw new HenryException("Failed to create new file");
            }
        } catch (IOException e) {
            throw new HenryException("An error occurred while accessing the file");
        }
    }

    /**
     * Reads tasks from the given file and adds them to the provided ArrayList.
     *
     * @param file The file to read tasks from.
     * @param recordedTasks The list where parsed tasks will be added.
     * @throws HenryException If there is an error reading the file.
     */
    private void readTasksFromFile(File file, ArrayList<Task> recordedTasks) throws HenryException {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                parseTask(scanner.nextLine(), recordedTasks);
            }
        } catch (IOException e) {
            throw new HenryException("An error occurred while reading the file");
        }
    }

    /**
     * Parses a single task from the input string and adds it to the list of recorded tasks.
     *
     * @param input The input string representing a task.
     * @param recordedTasks The list where the parsed task will be added.
     * @throws HenryException If the task type is unknown.
     */
    private void parseTask(String input, ArrayList<Task> recordedTasks) throws HenryException {
        String[] words = input.split(" \\| ");
        switch (words[0]) {
        case "T":
            addToDo(recordedTasks, words);
            break;
        case "D":
            addDeadline(recordedTasks, words);
            break;
        case "E":
            addEvent(words, recordedTasks);
            break;
        default:
            throw new HenryException("Unknown task type: " + words[0]);
        }
    }

    /**
     * Adds an Event task to the list.
     *
     * @param recordedTasks The list to store the all the tasks.
     * @param words The input string split into components.
     */
    private static void addEvent(String[] words, ArrayList<Task> recordedTasks) {
        String[] duration = words[3].split("-");
        String startTime = duration[0];
        String endTime = duration[1];
        recordedTasks.add(new Event(words[2], startTime,
                endTime, (Integer.parseInt(words[1]) != 0)));
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param recordedTasks The list to store the all the tasks.
     * @param words The input string split into components.
     */
    private static void addDeadline(ArrayList<Task> recordedTasks, String[] words) {
        recordedTasks.add(new Deadline(words[2], words[3], (Integer.parseInt(words[1]) != 0)));
    }

    /**
     * Adds a Todo task to the list.
     *
     * @param recordedTasks The list to store the all the tasks.
     * @param words The input string split into components.
     */
    private static void addToDo(ArrayList<Task> recordedTasks, String[] words) {
        recordedTasks.add(new Todo(words[2], (Integer.parseInt(words[1]) != 0)));
    }
}
