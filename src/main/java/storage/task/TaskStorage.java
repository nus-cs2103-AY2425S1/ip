package storage.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import exceptions.BrockException;
import storage.task.manager.DeadlineManager;
import storage.task.manager.EventManager;
import storage.task.manager.TaskManager;
import storage.task.manager.TodoManager;
import task.Task;

/**
 * Class to create, and interact with save file.
 */
public class TaskStorage {
    private static final String FILE_PATH = "./src/main/java/data/saveFile.txt";
    private static final File SAVE_FILE = new File(FILE_PATH);

    private static final Map<Character, TaskManager> TASK_MANAGERS = new HashMap<>();

    /**
     * Initializes the various task managers for each task type.
     */
    public TaskStorage() {
        TASK_MANAGERS.put('T', new TodoManager());
        TASK_MANAGERS.put('D', new DeadlineManager());
        TASK_MANAGERS.put('E', new EventManager());
    }

    /**
     * Resets the save file due to corrupted data.
     *
     * @param description String describing the corruption.
     * @throws BrockException Always throws this exception, containing message about the corruption.
     *                        To be bubbled up to the core.Brock.run() in the main class.
     */
    protected void resetSaveFile(String description) throws BrockException {
        this.writeToFile("", false);
        throw new BrockException("While reading from save file: \n"
                + description + '\n'
                + "Save file is corrupted. File has been reset!");
    }

    /**
     * Checks if the taskString is valid.
     *
     * @param taskString Task string to be examined.
     * @return The components within the task string, if found to be valid.
     * @throws BrockException If its invalid.
     */
    public String[] processTaskString(String taskString) throws BrockException {
        String[] taskComponents = taskString.split("\\. ", 2);
        if (taskComponents.length < 2) {
            this.resetSaveFile("Invalid task entry - missing task number!");
        }
        return taskComponents;
    }

    /**
     * Converts a task string into a corresponding {@code Task} object.
     *
     * @param taskString Task string being passed in.
     * @return Corresponding {@code Task} object.
     * @throws BrockException If task string is invalid.
     */
    private Task convertToTaskObject(String taskString) throws BrockException {
        String[] taskComponents = this.processTaskString(taskString);

        String taskDetails = taskComponents[1];
        char taskType = taskDetails.charAt(1);
        char taskStatus = taskDetails.charAt(4);
        // Remove the [<type>][<status>]
        // As we only want the task body
        String taskBody = taskDetails.substring(7);

        TaskManager taskManager = TASK_MANAGERS.get(taskType);
        if (taskManager == null) {
            this.resetSaveFile("Invalid task entry - unrecognized task type!");
            return null;
        }
        // CHECKSTYLE.OFF: Indentation
        try {
            return taskManager.convertToTaskObject(taskBody, taskStatus);
        } catch (BrockException e) {
            // Enter this block if there are any save file corruptions
            // Get the description of save file corruption and pass into the reset function
            this.resetSaveFile(e.getMessage());
            return null;
        }
        // CHECKSTYLE.ON: Indentation
    }

    /**
     * Converts all tasks in save file into corresponding {@code Task} objects.
     *
     * @return An {@code ArrayList<Task>} to store all objects.
     * @throws BrockException If unable to find save file.
     */
    public ArrayList<Task> loadTasksFromFile() throws BrockException, FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(SAVE_FILE);
        while (s.hasNext()) {
            String taskString = s.nextLine();
            Task task = this.convertToTaskObject(taskString);
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Handles the directory creation.
     *
     * @return The message describing the directory creation operation.
     */
    private String handleDirectory() {
        StringBuilder dirResult = new StringBuilder();

        String dirStatus;
        boolean isDirectoryMissing = SAVE_FILE.getParentFile().mkdirs();
        if (!isDirectoryMissing) {
            dirStatus = "Parent directories already exists!";
        } else {
            dirStatus = "Parent directories successfully created!";
        }

        dirResult.append("Creating parent directories for save file...\n")
                .append(dirStatus);
        return dirResult.toString();
    }

    /**
     * Handles the file creation.
     *
     * @return The message describing the file creation operation.
     * @throws IOException If there are issues creating a new save file.
     */
    private String handleFile() throws IOException {
        StringBuilder fileResult = new StringBuilder();

        String fileStatus;
        boolean isFileMissing = SAVE_FILE.createNewFile();
        if (!isFileMissing) {
            fileStatus = "Save file already exists!";
        } else {
            fileStatus = "Save file successfully created";
        }

        fileResult.append("Creating save file for tasks...\n")
                .append(fileStatus);
        return fileResult.toString();
    }

    /**
     * Checks if the save file and parent directories are present.
     * If they are not, create them accordingly.
     *
     * @return {@code String[]} of size 2.
     *      First element is directory result, second element is file result.
     * @throws IOException If there were issues creating the files and folders.
     */
    public String[] createFile() throws IOException {
        String dirResultString = this.handleDirectory();
        String fileResultString = this.handleFile();
        return new String[]{dirResultString, fileResultString};
    }

    /**
     * Writes to the save file, to update it when there are new tasks or deleted tasks.
     *
     * @param writeContent The task to be written.
     * @param isAppendMode Option to append to existing content, or overwrite existing content.
     * @throws BrockException If there are issues with writing to the file.
     */
    public void writeToFile(String writeContent, boolean isAppendMode) throws BrockException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, isAppendMode);
            fw.write(writeContent);
            fw.close();
        } catch (IOException e) {
            throw new BrockException(e.getMessage());
        }
    }
}
