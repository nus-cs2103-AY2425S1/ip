package stelle;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import stelle.exception.NoSuchTaskException;
import stelle.task.Task;

/**
 * Represents a list of Tasks.
 * @author Lee Ze Hao (A0276123J)
 */

public class TaskList {
    private Storage storage;
    private ArrayList<Task> taskList = new ArrayList<Task>();

    /**
     * Constructs a stelle.TaskList object.
     * Tries to get list of tasks from file path. If it does not exist, create an empty save file.
     * @param filePath Path where the local task list file is stored.
     */
    public TaskList(String filePath) {
        storage = new Storage(filePath);

        try {
            taskList = storage.readTasksFromFile();
        } catch (FileNotFoundException e) {
            storage.writeTasksToFile(taskList);
        }
    }

    /**
     * Returns the size of the task list.
     * @return int Size of task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets the task at the specified index in the list.
     * @param index The list number of the task.
     * @return stelle.task.Task the task at the specific index
     */
    public Task get(int index) {
        if (index >= taskList.size() || index < 0) {
            throw new NoSuchTaskException();
        }
        return taskList.get(index);
    }

    /**
     * Adds the task to the end of the task list.
     * @param task The task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes the task at the specified list number.
     * @param index The list number of the task to be deleted.
     */
    public void remove(int index) {
        if (index >= taskList.size() || index < 0) {
            throw new NoSuchTaskException();
        }
        taskList.remove(index);
    }

    /**
     * Gets the last task in the list.
     */
    public Task getLastTask() {
        if (taskList.size() <= 0) {
            throw new NoSuchTaskException();
        }
        return taskList.get(taskList.size() - 1);
    }

    /**
     * Updates task list according to file. If file not found, creates a blank file.
     * File location is as defined during construction.
     */
    public void readFromFile() {
        try {
            taskList = storage.readTasksFromFile();
        } catch (FileNotFoundException e) {
            storage.writeTasksToFile(taskList);
        }
    }

    /**
     * Writes the current task list to a local file. If file is not found, creates it.
     * File location is as defined during construction.
     */
    public void writeToFile() {
        storage.writeTasksToFile(taskList);
    }

    /**
     * Returns an ArrayList containing all list numbers of tasks with names that contain the query.
     * @param query The string being searched for.
     * @return ArrayList[Integer] ArrayList containing all list numbers of tasks with names that contain the query.
     */
    public ArrayList<Integer> find(String query) {
        ArrayList<Integer> resultIndexes = new ArrayList<Integer>();

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String lowerCaseTaskName = task.getTaskName().toLowerCase();
            String lowerCaseQuery = query.toLowerCase();
            if (lowerCaseTaskName.contains(lowerCaseQuery)) {
                resultIndexes.add(i);
            }
        }

        return resultIndexes;
    }
}
