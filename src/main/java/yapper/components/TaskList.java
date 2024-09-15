package yapper.components;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import yapper.exceptions.YapperException;

/**
 * Manages a list of tasks, including adding, deleting, marking, and listing tasks.
 * Handles writing the task list to a file.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private String filePath;

    /**
     * Constructs a TaskList with the specified list of tasks and file path.
     *
     * @param taskList the initial list of tasks
     * @param filePath the path to the file where tasks will be saved
     */
    public TaskList(ArrayList<Task> taskList, String filePath) {
        this.taskList = taskList;
        this.filePath = filePath;
    }

    /**
     * Adds a new task to the task list and updates the file.
     *
     * @param task the task to be added
     * @return     a string reflecting the changes
     */
    public String addTask(Task task) {
        assert task != null : "Task should not be null";
        this.taskList.add(task);
        String[] texts = {
            "Task has been added:",
            "  " + task,
            "A total of " + getSize() + " " + pluralise() + " are on the list."
        };
        writeToFile();
        return stringifyResponse(texts);
    }

    /**
     * Returns the appropriate plural form of the word "task" based on the number of tasks in the list.
     *
     * @return a string representing "task" or "tasks"
     */
    public String pluralise() {
        String taskMessage = "task";
        if (getSize() != 1) {
            taskMessage += "s";
        }
        return taskMessage;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the size of the task list
     */
    public int getSize() {
        assert this.taskList != null : "Task list should not be null";
        return this.taskList.size();
    }

    /**
     * Retrieves a task from the list by its index.
     *
     * @param taskNumber the 1-indexed number of the task to retrieve (uses 1-indexed numbering)
     * @return the task at the specified index
     */
    public Task getTask(int taskNumber) throws YapperException {
        assert this.taskList != null : "Task list should not be null";
        assert taskNumber > 0 : "Task number should not be negative";
        assert taskNumber <= getSize() : "Task number should not be greater than length of list";
        if (taskNumber <= 0 || taskNumber > getSize()) {
            throw new YapperException("Oopsie! Couldn't find that one! :)");
        }
        return this.taskList.get(taskNumber - 1);
    }

    /**
     * Lists all tasks currently in the task list, displaying their index and description.
     * @return a list of all tasks
     */
    public String listTasks() {
        assert this.taskList != null : "Task list should not be null";
        StringBuilder sb = new StringBuilder();
        String header = String.format("You currently have %d %s.\n", getSize(), pluralise());
        sb.append(header);
        for (int i = 1; i <= getSize(); i++) {
            String temp = i + "." + getTask(i) + "\n";
            sb.append(temp);
        }
        return sb.toString();
    }

    /**
     * Deletes a task from the list based on the specified task number and updates the file.
     *
     * @param taskNumber the number of the task to be deleted
     * @return           a of string reflecting the changes
     */
    public String deleteTask(int taskNumber) throws YapperException {
        Task task = getTask(taskNumber);
        this.taskList.remove(taskNumber - 1);
        writeToFile();
        String[] texts = {
            "Task removed:",
            "  " + task,
            "And you still have " + getSize() + " " + pluralise() + " are still left."
        };
        return stringifyResponse(texts);
    }

    /**
     * Marks or unmarks a task as completed based on the command and task number, and updates the file.
     *
     * @param command     the command specifying whether to mark or unmark the task
     * @param taskNumber  the number of the task to be marked or unmarked
     * @return            a string reflecting the changes
     */
    public String markOrUnmarkTask(String command, int taskNumber) throws YapperException {
        Task task = getTask(taskNumber);
        String message = "";
        if (command.equals("mark")) {
            message = "Done! This task is now marked completed:";
            task.mark();
        } else {
            message = "Task reopened! But why though?";
            task.unmark();
        }
        writeToFile();
        String[] texts = {
            message,
            " " + task,
        };
        return stringifyResponse(texts);
    }

    /**
     * Finds and prints out the tasks that contain a certain keyword
     *
     * @param keyword     the keyword used to filter out the relevant tasks
     * @return            the strings representing each task that contains the provided keyword
     */
    public String findTasks(String keyword) {
        ArrayList<Task> foundMatches = this.taskList.stream()
            .filter(task -> task.toString().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toCollection(ArrayList::new));

        if (foundMatches.isEmpty()) {
            return "Sorry, I couldn't find any tasks with that keyword! :(";
        }
        return new TaskList(foundMatches, "").listTasks();
    }

    /**
     * Clears the task list of all tasks
     * @return an string reflecting the changes
     */
    public String clearTasks() {
        this.taskList.clear();
        writeToFile();
        return "Got rid of all tasks. Starting anew?";
    }

    /**
     * Writes the current list of tasks to the file.
     * Each task is written on a new line.
     */
    public void writeToFile() {
        assert filePath != null : "The path to the file should not be null";
        assert !filePath.isEmpty() : "The path to the file should not be an empty string";
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (Task t : this.taskList) {
                fileWriter.write(t.getDesc() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a string indicating the action that was performed on the task list
     * @param texts a variable number of strings to concatenate with the new-line character
     * @return      a string concatenation
     */
    public String stringifyResponse(String... texts) {
        StringBuilder response = new StringBuilder();
        for (String text : texts) {
            response.append(text).append("\n");
        }
        return response.toString();
    }
}
