package denim.storage;

import denim.TaskList;
import denim.exceptions.DenimException;
import denim.tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteTaskFile {

    private final File taskFile;

    public WriteTaskFile(String pathname) {
        taskFile = new File(pathname);
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
}
