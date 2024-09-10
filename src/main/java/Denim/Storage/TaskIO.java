package denim.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import denim.TaskList;
import denim.exceptions.DenimException;
import denim.tasks.Deadline;
import denim.tasks.Event;
import denim.tasks.Task;
import denim.tasks.Todo;

/**
 * Handles the input and output operations for task data in the Denim application.
 * This class is responsible for reading tasks from and writing tasks to a file,
 * managing file creation, and processing task data.
 */
public class TaskIo {

    /**
     * Represents the status of the file or directory during task I/O operations.
     */
    enum FileStatus {
        DIRECTORY_DOES_NOT_EXIST,
        FILE_DOES_NOT_EXIST
    }

    static final String TODO = "T";
    static final String EVENT = "E";
    static final String DEADLINE = "D";

    private final File taskFile;

    /**
     * Constructs a TaskIO object associated with the specified file path.
     *
     * @param pathname The path to the file where tasks are stored.
     */
    public TaskIo(String pathname) {
        taskFile = new File(pathname);
    }

    /**
     * Creates a save point by handling directory or file creation based on the provided status.
     *
     * @param status The status indicating whether the directory or file does not exist.
     * @param sc     The scanner to receive user input for creating the necessary files.
     * @throws DenimException If an error occurs during file or directory creation.
     */
    private void createSavePoint(FileStatus status, Scanner sc) throws DenimException {
        switch (status) {
        case DIRECTORY_DOES_NOT_EXIST:
            handleDirectoryNotFound(sc);
            break;
        case FILE_DOES_NOT_EXIST:
            handleFileNotFound(sc);
            break;
        default:
            throw new DenimException("An error has occurred during the creation of files. Terminating");
        }
    }

    /**
     * Handles the case where the directory is not found.
     * Prompts the user to create the directory and the corresponding file.
     *
     * @param sc The scanner to receive user input for directory and file creation.
     * @throws DenimException If the directory or file cannot be created.
     */
    private void handleDirectoryNotFound(Scanner sc) throws DenimException {
        System.out.println("data directory and corresponding denim.txt not found. Create both? (y / n)\n");
        String input = sc.nextLine();

        switch (input) {
        case "y":
            File directory = new File("data");
            directory.mkdir();
            File dataFile = new File(directory, "denim.txt");
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new DenimException("Unable to create denim.txt");
            }

            break;
        case "n":
        default:
            throw new DenimException("Terminating Program. Have a nice day.");
        }
    }

    /**
     * Handles the case where the file is not found.
     * Prompts the user to create the file within an existing directory.
     *
     * @param sc The scanner to receive user input for file creation.
     * @throws DenimException If the file cannot be created.
     */
    private void handleFileNotFound(Scanner sc) throws DenimException {
        System.out.println("denim.txt not found in data directory. Create denim.txt? (y / n)\n");
        String input = sc.nextLine();
        switch (input) {
        case "y":
            File denimFile = new File("data", "denim.txt");
            try {
                denimFile.createNewFile();
            } catch (IOException e) {
                throw new DenimException("Unable to create denim.txt");
            }
            break;
        case "n":
            //Fallthrough
        default:
            throw new DenimException("Terminating Program. Have a nice day.");
        }
    }

    /**
     * Reads task data from the file and populates the provided task list.
     *
     * @param taskList The task list to populate with data from the file.
     * @param sc       The scanner to receive user input for file creation if needed.
     * @throws DenimException If an error occurs during file reading or if the file/directory does not exist.
     */
    public void readTaskData(TaskList taskList, Scanner sc) throws DenimException {

        // Checks for Parent Directory ./data
        File dataDirectory = taskFile.getParentFile();
        if (dataDirectory == null || !dataDirectory.isDirectory()) {
            createSavePoint(FileStatus.DIRECTORY_DOES_NOT_EXIST, sc);
            return;
        }

        //Checks for denim.txt file
        if (!taskFile.exists()) {
            createSavePoint(FileStatus.FILE_DOES_NOT_EXIST, sc);
            return;
        }

        // Both data directory and denim.txt exists. Proceed to read from denim.txt
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
            sc.close();
            throw new DenimException("An error has occurred while trying to read denim.txt\n Terminating Program.");
        }
    }

    /**
     * Writes the specified task to the file.
     *
     * @param task The task to be written to the file.
     * @throws DenimException If an error occurs during writing to the file.
     */
    public void writeTaskData(Task task) throws DenimException {
        try {
            FileWriter taskWriter = new FileWriter(taskFile, true);
            taskWriter.write(task.toSimplifiedString());
            taskWriter.close();
        } catch (IOException e) {
            throw new DenimException("Unable to write to denim.txt");
        }
    }

    /**
     * Deletes all tasks from the file and rewrites the current task list.
     *
     * @param taskList The task list containing the tasks to be written to the file.
     * @throws DenimException If an error occurs during file overwriting.
     */
    public void deleteTask(TaskList taskList) throws DenimException {
        File overridingFile = new File("data", "denim.txt");
        Task task;
        try {
            FileWriter fw = new FileWriter(overridingFile);
            for (int i = 0; i < taskList.getTaskListSize(); i++) {
                task = taskList.getTask(i);
                fw.write(task.toSimplifiedString());
            }
            fw.close();
        } catch (IOException e) {
            throw new DenimException("Error has occurred while trying to overwrite denim.txt.");
        }
    }

    /**
     * Marks the specified tasks in the task list and updates the file.
     *
     * @param taskList The task list containing the tasks to be marked.
     * @throws DenimException If an error occurs during file overwriting.
     */
    public void markTask(TaskList taskList) throws DenimException {
        File overridingFile = new File("data", "denim.txt");
        Task task;
        try {
            FileWriter fw = new FileWriter(overridingFile);
            for (int i = 0; i < taskList.getTaskListSize(); i++) {
                task = taskList.getTask(i);
                fw.write(task.toSimplifiedString());
            }
            fw.close();
        } catch (IOException e) {
            throw new DenimException("Error has occurred while trying to overwrite denim.txt.");
        }
    }

    /**
     * Unmarks the specified tasks in the task list and updates the file.
     *
     * @param taskList The task list containing the tasks to be unmarked.
     * @throws DenimException If an error occurs during file overwriting.
     */
    public void unmarkTask(TaskList taskList) throws DenimException {
        File overridingFile = new File("data", "denim.txt");
        Task task;
        try {
            FileWriter fw = new FileWriter(overridingFile);
            for (int i = 0; i < taskList.getTaskListSize(); i++) {
                task = taskList.getTask(i);
                fw.write(task.toSimplifiedString());
            }
            fw.close();
        } catch (IOException e) {
            throw new DenimException("Error has occurred while trying to overwrite denim.txt.");
        }
    }

    /**
     * Processes a task description from the file and adds it to the task list.
     *
     * @param taskList The task list to which the task will be added.
     * @param task     The task description to be processed.
     * @throws DenimException If the task type is unknown or if there is a formatting error.
     */
    private void processTask(TaskList taskList, String task) throws DenimException {
        String[] taskComponents = task.split("\\|");
        String taskType = taskComponents[0].trim();
        boolean taskStatus = taskComponents[1].trim().equals("1");
        String taskDescription = taskComponents[2].trim();
        Task incomingTask;

        switch (taskType) {
        case TODO:
            incomingTask = new Todo(taskDescription, taskStatus);
            taskList.addTask(incomingTask);
            break;
        case DEADLINE:
            String deadlineBy = taskComponents[3].trim();
            DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
            LocalDateTime deadline = LocalDateTime.parse(deadlineBy, deadlineFormatter);
            incomingTask = new Deadline(taskDescription, taskStatus, deadline);
            taskList.addTask(incomingTask);
            break;
        case EVENT:
            DateTimeFormatter eventFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
            LocalDateTime eventFrom = LocalDateTime.parse(taskComponents[3].trim(), eventFormatter);
            LocalDateTime eventTo = LocalDateTime.parse(taskComponents[4].trim(), eventFormatter);
            incomingTask = new Event(taskDescription, taskStatus, eventFrom, eventTo);
            taskList.addTask(incomingTask);
            break;
        default:
            throw new DenimException("Unknown Formatting in data/denim.txt");
        }
    }
}
