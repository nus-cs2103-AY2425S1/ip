package meep.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import meep.MeepException;
import meep.ui.Ui;

/**
 * The {@code Storage} class handles the loading and saving of tasks to and from a file.
 * It manages the storage of task data, ensuring that tasks are persisted between sessions.
 */
public class Storage {
    private final String dataPath;
    private final String archivePath;

    /**
     * Constructs a {@code Storage} object with the specified file path for task data.
     *
     * @param dataPath The file path where task data will be stored.
     */
    public Storage(String dataPath) {
        this.dataPath = dataPath;
        this.archivePath = dataPath + ".archive";
    }

    /**
     * Processes a task string and adds the corresponding task to the specified {@code TaskList}.
     * The task string is expected to be in the format used for saving tasks to a file.
     *
     * @param taskString The string representation of the task.
     * @param taskList   The {@code TaskList} to which the task will be added.
     */
    private static void processTaskString(String taskString, TaskList taskList) {
        try {
            String[] taskParts = taskString.split("\\|");
            String taskType = taskParts[0];
            boolean isDone = taskParts[1].equals("1");
            String taskDescription = taskParts[2];
            Task task;
            switch (taskType) {
            case "T":
                task = new Todo(taskDescription);
                if (isDone) {
                    task.markAsDone();
                }
                taskList.addItem(task);
                break;
            case "D":
                task = new Deadline(taskDescription, taskParts[3]);
                if (isDone) {
                    task.markAsDone();
                }
                taskList.addItem(task);
                break;
            case "E":
                task = new Event(taskDescription, taskParts[3], taskParts[4]);
                if (isDone) {
                    task.markAsDone();
                }
                taskList.addItem(task);
                break;
            default:
                break;
            }
        } catch (IndexOutOfBoundsException e) {
            Ui ui = new Ui();
            ui.errorLoadingTask();
        }
    }

    /**
     * Creates the file at the specified path if it does not already exist.
     *
     * @throws MeepException If an error occurs while creating the file.
     */
    private void createFileIfNotExists() throws MeepException {
        File file = new File(this.dataPath);
        try {
            if (file.exists()) {
                return;
            }
            if (!file.createNewFile()) {
                throw new IOException("Failed to create the file");
            }
        } catch (IOException e) {
            throw new MeepException("An error occurred." + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file and returns them as a {@code TaskList}.
     * If the file does not exist, it will be created.
     *
     * @return A {@code TaskList} containing the tasks loaded from the file.
     * @throws MeepException If an error occurs while loading tasks.
     */
    public TaskList loadTasks() throws MeepException {
        createFileIfNotExists();
        TaskList taskList = new TaskList();
        try {
            File f = new File(this.dataPath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                processTaskString(s.nextLine(), taskList);
            }
        } catch (FileNotFoundException e) {
            throw new MeepException("File not found: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * Saves the tasks in the specified {@code TaskList} to the file.
     *
     * @param taskList The {@code TaskList} to be saved.
     * @throws MeepException If an error occurs while saving tasks.
     */
    public void saveTasks(TaskList taskList) throws MeepException {
        try {
            FileWriter fw = new FileWriter(this.dataPath);
            fw.write(taskList.getSaveFormatList());
            fw.close();
        } catch (IOException e) {
            throw new MeepException("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Archives a task by moving it to the archive file.
     *
     * @param task The task to be archived.
     * @throws MeepException If an error occurs while archiving the task.
     */
    public void archiveTask(Task task) throws MeepException {
        try {
            FileWriter fw = new FileWriter(this.archivePath, true);
            fw.write(task.getSaveFormat() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new MeepException("An error occurred: " + e.getMessage());
        }
    }
}
