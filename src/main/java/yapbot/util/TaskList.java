package yapbot.util;

import yapbot.exceptions.YapBotException;
import yapbot.tasks.Deadline;
import yapbot.tasks.Event;
import yapbot.tasks.Task;
import yapbot.tasks.ToDo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private ArrayList<Task> storedTasks;

    public TaskList() {
        this.storedTasks = new ArrayList<>();
    }

    /**
     * Loads tasks from file and creates the file if its does not exist.
     * If the file cannot be created, YapBot continues running but tasks will not be saved.
     * If load file is corrupted, previously loaded tasks are cleared from storedTasks and the file is overwritten.
     *
     * @return true if data is loaded successfully and false otherwise.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.storedTasks = tasks;
    }

    /**
     * Creates a task instance and stores it in storedTasks field.
     *
     * @param tasktype Type of task to be created (eg. ToDo, Event, etc).
     * @param taskDetails Details of the task to be created.
     * @throws YapBotException If task details are missing for the specified task type.
     */
    public void addTask(Task task) throws YapBotException {
        this.storedTasks.add(task);

    }

    /**
     * Prints the list of tasks currently in storedTasks.
     *
     * @throws YapBotException If storedTasks is empty.
     */
    public String listTasks() throws YapBotException {
        if (storedTasks.isEmpty()) {
            throw new YapBotException("Error, no Tasks found in database.");
        }
        StringBuilder result = new StringBuilder();
        Iterator<Task> iterateStored = storedTasks.iterator();

        int index = 1;
        while (iterateStored.hasNext()) {
            result.append("  ")
                    .append(index)
                    .append(".")
                    .append(iterateStored.next())
                    .append("\n");
            index++;
        }

        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    /**
     * Saves the tasks stored in storedTasks in a text file.
     *
     * @throws IOException If the file cannot be found.
     */
    public String saveTasks() {

        if (storedTasks.isEmpty()) {
            return "";
        }

        Iterator<Task> iterateStored = storedTasks.iterator();
        StringBuilder result = new StringBuilder();

        while (iterateStored.hasNext()) {
            result.append(iterateStored.next().saveTask())
                    .append(System.lineSeparator());
        }

        return result.toString();
    }

    /**
     * Removes task in storedTasks.
     *
     * @param index Task number specifed by user. index = actual array index + 1.
     * @throws YapBotException If index > size of storedTasks array or index <= 0 (ie there is no task for the index).
     */
    public Task deleteTask(int index) throws YapBotException {
        if (index < 0 || index >= storedTasks.size()) {
            throw new YapBotException("Finding Task...Failure\nError, requested Task does not exist"
                    + ".\nUse command \"list\" to view your tasks.");
        }

        return storedTasks.remove(index);
    }

    /**
     * Sets a task to either completed or incomplete.
     *
     * @param index Index of task to be marked.
     * @throws YapBotException If index >= size of storedTasks array or index < 0 (ie there is no task for the index).
     */
    public Task markTask(int index) throws YapBotException {
        if (index < 0 || index >= storedTasks.size()) {
            throw new YapBotException("Finding Task...Failure\nError, requested Task does not exist"
                    + ".\nUse command \"list\" to view your tasks.");
        }

        Task task = storedTasks.get(index);
        task.setDone(true);

        return task;
    }

    public Task unmarkTask(int index) throws YapBotException {
        if (index < 0 || index >= storedTasks.size()) {
            throw new YapBotException("Finding Task...Failure\nError, requested Task does not exist"
                    + ".\nUse command \"list\" to view your tasks.");
        }

        Task task = storedTasks.get(index);
        task.setDone(false);

        return task;
    }

    public String getMatchingTasks(String query) {
        Iterator<Task> tasks = this.storedTasks.iterator();

        StringBuilder result = new StringBuilder();
        int index = 1;

        while (tasks.hasNext()) {
            Task task = tasks.next();
            if (task.checkTaskname(query)) {
                result.append("  ")
                        .append(index)
                        .append(".")
                        .append(task)
                        .append("\n");
                index++;
            }
        }

        if (result.isEmpty()) {
            return null;
        } else {
            result.deleteCharAt(result.length() - 1);
            return result.toString();
        }
    }

    public int size() {
        return this.storedTasks.size();
    }

}
