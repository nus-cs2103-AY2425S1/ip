package denim.storage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import denim.TaskList;
import denim.exceptions.DenimDirectoryException;
import denim.exceptions.DenimException;
import denim.exceptions.DenimFileCorruptionException;
import denim.exceptions.DenimFileException;
import denim.tasks.Deadline;
import denim.tasks.Event;
import denim.tasks.Task;
import denim.tasks.Todo;

/**
 * Handles the input operations for task data in the Denim application.
 * This class is responsible for reading tasks from a file and processing task data.
 */
public class ReadTaskFile {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    private static final String TODO = "T";
    private static final String EVENT = "E";
    private static final String DEADLINE = "D";

    private static final Pattern TODO_PATTERN = Pattern.compile(
            "^(?<taskType>T) \\| (?<taskStatus>[01]) \\| (?<taskDescription>.+)$"
    );

    private static final Pattern DEADLINE_PATTERN = Pattern.compile(
            "^(?<taskType>D) \\| (?<taskStatus>[01]) \\| (?<taskDescription>[^|]+) \\| (?<dateTime>[^|]+)$"
    );

    private static final Pattern EVENT_PATTERN = Pattern.compile(
            "^(?<taskType>E) \\| (?<taskStatus>[01]) \\| (?<taskDescription>[^|]+) \\| "
                    + "(?<startDateTime>[^|]+) \\| (?<endDateTime>[^|]+)$"
    );


    private final File taskFile;

    /**
     * Creates a new ReadTaskFile object with the given path name.
     *
     * @param pathname the path of the file intended to be read.
     */
    public ReadTaskFile(String pathname) {
        taskFile = new File(pathname);
    }

    /**
     *
     */
    private boolean isTaskFileDataValid(String fileData) {

        if (isTaskDataATodo(fileData)) {
            return true;
        }

        if (isTaskDataAnEvent(fileData)) {
            return true;
        }

        if (isTaskDataADeadline(fileData)) {
            return true;
        }

        return false;
    }

    private boolean isTaskDataATodo(String fileData) {
        Matcher matcher = TODO_PATTERN.matcher(fileData);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    private boolean isTaskDataAnEvent(String fileData) {
        Matcher matcher = EVENT_PATTERN.matcher(fileData);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    private boolean isTaskDataADeadline(String fileData) {
        Matcher matcher = DEADLINE_PATTERN.matcher(fileData);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }


    /**
     * Reads task data from the file and populates the provided task list.
     *
     * @param taskList The task list to populate with data from the file.
     * @throws DenimException If an error occurs during file reading or if the file/directory does not exist.
     */
    public void readTaskData(TaskList taskList) throws DenimFileException, DenimDirectoryException,
            DenimFileCorruptionException {

        if (!doesDirectoryExist()) {
            throw new DenimDirectoryException("Directory Not Found");
        }

        if (!doesFileExist()) {
            throw new DenimFileException("File Not Found");
        }

        updateTaskList(taskList);
    }

    /**
     * Checks if the parent directory exists.
     */
    private boolean doesDirectoryExist() {
        return taskFile.getParentFile().exists();
    }

    /**
     * Checks if the file exists.
     */
    private boolean doesFileExist() {
        return taskFile.exists();
    }

    /**
     * Handles the case where the directory is not found.
     * Prompts the user to create the directory and the corresponding file.
     *
     * @throws DenimException If the directory or file cannot be created.
     */
    public void handleDirectoryNotFound() throws DenimDirectoryException {
        File directory = new File("data");
        directory.mkdir();
        File dataFile = new File(directory, "denim.txt");
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            throw new DenimDirectoryException("Unable to create denim.txt");
        }
    }

    /**
     * Handles the case where the file is not found.
     * Prompts the user to create the file within an existing directory.
     *
     * @throws DenimException If the file cannot be created.
     */
    public void handleFileNotFound() throws DenimFileException {
        File denimFile = new File("data", "denim.txt");
        try {
            denimFile.createNewFile();
        } catch (IOException e) {
            throw new DenimFileException("Unable to create denim.txt");
        }
    }

    /**
     * Handles the case where the file is corrupted.
     * Prompts the user to whether to create a new file.
     *
     * @throws DenimException If the file cannot be created.
     */
    public void handleFileCorruption() throws DenimFileException {
        File denimFile = new File("data", "denim.txt");
        File corruptedDataFile = new File("data", "corrupted.txt");

        corruptedDataFile.delete();
        denimFile.renameTo(corruptedDataFile);

        File newDenimFile = new File("data", "denim.txt");
        try {
            newDenimFile.createNewFile();
        } catch (IOException e) {
            throw new DenimFileException("Unable to create denim.txt");
        }
    }

    private void updateTaskList(TaskList taskList) throws DenimFileException, DenimFileCorruptionException {
        try {
            Scanner fileReader = new Scanner(taskFile);
            while (fileReader.hasNext()) {
                String taskDescription = fileReader.nextLine();
                if (taskDescription.equals("")) {
                    continue;
                }
                processTask(taskList, taskDescription);
            }
        } catch (IOException e) {
            throw new DenimFileException("An error has occurred while trying to read denim.txt\n Terminating Program.");
        }
    }

    /**
     * Processes a task description from the file and adds it to the task list.
     *
     * @param taskList The task list to which the task will be added.
     * @param task     The task description to be processed.
     * @throws DenimException If the task type is unknown or if there is a formatting error.
     */
    private void processTask(TaskList taskList, String task) throws DenimFileException, DenimFileCorruptionException {

        if (!isTaskFileDataValid(task)) {
            taskList.clearTaskList();
            throw new DenimFileCorruptionException("Corrupted File");
        }

        String[] taskComponents = task.split("\\|");
        String taskType = taskComponents[0].trim();
        boolean taskStatus = taskComponents[1].trim().equals("1");
        String taskDescription = taskComponents[2].trim();

        switch (taskType) {
        case TODO:
            taskList.addTask(produceTodoTask(taskDescription, taskStatus));
            break;
        case DEADLINE:
            String deadline = taskComponents[3].trim();
            taskList.addTask(produceDeadlineTask(taskDescription, taskStatus, deadline));
            break;
        case EVENT:
            String eventFrom = taskComponents[3].trim();
            String eventTo = taskComponents[4].trim();
            taskList.addTask(produceEventTask(taskDescription, taskStatus, eventFrom, eventTo));
            break;
        default:
            throw new DenimFileException("Unknown Formatting in data/denim.txt");
        }
    }

    /**
     * Produces a deadline Task from the data being read from the file.
     *
     * @param taskDescription the description of the task.
     * @param taskStatus the completion status of the task.
     * @param deadline the unformatted deadline of the task.
     */
    public Task produceDeadlineTask(String taskDescription, boolean taskStatus, String deadline) {
        LocalDateTime formattedDeadline = LocalDateTime.parse(deadline, ReadTaskFile.DATE_TIME_FORMATTER);
        return new Deadline(taskDescription, taskStatus, formattedDeadline);
    }

    /**
     * Produces an Event Task from the data being read from the file.
     *
     * @param taskDescription the description of the task.
     * @param taskStatus the completion status of the task.
     * @param from the unformatted date and time of the task.
     * @param to the unformatted date and time of the task.
     */
    public Task produceEventTask(String taskDescription, boolean taskStatus, String from, String to) {
        LocalDateTime formattedEventFrom = LocalDateTime.parse(from, ReadTaskFile.DATE_TIME_FORMATTER);
        LocalDateTime formattedEventTo = LocalDateTime.parse(to, ReadTaskFile.DATE_TIME_FORMATTER);
        return new Event(taskDescription, taskStatus, formattedEventFrom, formattedEventTo);
    }

    /**
     * Produces a todo Task from the data being read from the file.
     *
     * @param taskDescription the description of the task.
     * @param taskStatus the completion status of the task.
     */
    public Task produceTodoTask(String taskDescription, boolean taskStatus) {
        return new Todo(taskDescription, taskStatus);
    }
}
