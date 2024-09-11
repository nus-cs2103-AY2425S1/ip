package denim.storage;

import denim.TaskList;
import denim.exceptions.DenimDirectoryException;
import denim.exceptions.DenimException;
import denim.exceptions.DenimFileException;
import denim.tasks.Deadline;
import denim.tasks.Event;
import denim.tasks.Task;
import denim.tasks.Todo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ReadTaskFile {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    static final String TODO = "T";
    static final String EVENT = "E";
    static final String DEADLINE = "D";

    enum FileStatus {
        DIRECTORY_DOES_NOT_EXIST,
        FILE_DOES_NOT_EXIST
    }

    private final File taskFile;

    public ReadTaskFile(String pathname) {
        taskFile = new File(pathname);
    }

    /**
     * Reads task data from the file and populates the provided task list.
     *
     * @param taskList The task list to populate with data from the file.
     * @throws DenimException If an error occurs during file reading or if the file/directory does not exist.
     */
    public void readTaskData(TaskList taskList) throws DenimFileException, DenimDirectoryException {

        if (!doesDirectoryExist()) {
            throw new DenimDirectoryException("Directory Not Found");
        }

        if (!doesFileExist()) {
            throw new DenimFileException("File Not Found");
        }

        updateTaskList(taskList);
    }

    private boolean doesDirectoryExist() {
        return taskFile.getParentFile().exists();
    }

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

    private void updateTaskList(TaskList taskList) throws DenimFileException {
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
    private void processTask(TaskList taskList, String task) throws DenimFileException {
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
            String eventFrom = taskComponents[4].trim();
            String eventTo = taskComponents[5].trim();
            taskList.addTask(produceEventTask(taskDescription, taskStatus, eventFrom, eventTo));
            break;
        default:
            throw new DenimFileException("Unknown Formatting in data/denim.txt");
        }
    }

    public Task produceDeadlineTask(String taskDescription, boolean taskStatus, String deadline) {
        LocalDateTime formattedDeadline = LocalDateTime.parse(deadline, ReadTaskFile.DATE_TIME_FORMATTER);
        return new Deadline(taskDescription, taskStatus, formattedDeadline);
    }

    public Task produceEventTask(String taskDescription, boolean taskStatus, String from, String to) {
        LocalDateTime formattedEventFrom = LocalDateTime.parse(from, ReadTaskFile.DATE_TIME_FORMATTER);
        LocalDateTime formattedEventTo = LocalDateTime.parse(to, ReadTaskFile.DATE_TIME_FORMATTER);
        return new Event(taskDescription, taskStatus, formattedEventFrom, formattedEventTo);
    }

    public Task produceTodoTask(String taskDescription, boolean taskStatus) {
        return new Todo(taskDescription, taskStatus);
    }
}
