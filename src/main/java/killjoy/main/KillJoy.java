package killjoy.main;

import java.time.LocalDateTime;
import java.util.ArrayList;

import killjoy.processing.ProcessTasks;
import killjoy.processing.Storage;
import killjoy.task.Deadline;
import killjoy.task.Event;
import killjoy.task.Task;
import killjoy.task.Todo;

/**
 * Represents the main class of the KillJoy application.
 */
public class KillJoy {
    private ProcessTasks processTasks;
    private Storage saveAndLoad;
    private UserInterface ui;
    private ArrayList<Task> taskList;
    private int taskCount = 0;

    /**
     * Constructor for the KillJoy class.
     */
    public KillJoy() {
        this.ui = new UserInterface(this);
        this.processTasks = new ProcessTasks(this, ui);
        this.saveAndLoad = new Storage(processTasks);
        this.taskList = new ArrayList<>();
    }

    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Increases the task count by 1.
     */
    public void increaseTaskCount() {
        this.taskCount++;
    }

    /**
     * Decreases the task count by 1.
     */
    public void decreaseTaskCount() {
        this.taskCount--;
    }

    /**
     * Adds a task to the task list.
     * @param description
     */
    public void addTask(String description) {
        assert !description.equals("") : "Description cannot be empty";
        taskList.add(new Todo(description));
        this.increaseTaskCount();
    }

    /**
     * Adds a task to the task list.
     *
     * @param description
     * @param by
     */
    public void addTask(String description, LocalDateTime by) {
        assert !description.equals("") : "Description cannot be empty";
        assert by != null : "By date cannot be null";
        taskList.add(new Deadline(description, by));
        this.increaseTaskCount();
    }

    /**
     * Adds a task to the task list.
     *
     * @param description
     * @param from
     * @param to
     */
    public void addTask(String description, LocalDateTime from, LocalDateTime to) {
        assert !description.equals("") : "Description cannot be empty";
        assert from != null : "From date cannot be null";
        assert to != null : "To date cannot be null";
        taskList.add(new Event(description, from, to));
        this.increaseTaskCount();
    }

    public Task getTask(int taskIndex) {
        assert taskIndex >= 0 && taskIndex < this.taskList.size() : "Invalid task index";
        return this.taskList.get(taskIndex);
    }

    /**
     * Removes a task from the task list.
     *
     * @param taskIndex
     */
    public void removeTask(int taskIndex) {
        assert taskIndex >= 0 && taskIndex < this.taskList.size() : "Invalid task index";
        this.taskList.remove(taskIndex);
        this.decreaseTaskCount();
    }

    /**
     * Loads tasks from the save file.
     */
    public void loadTasks(String fileName) {
        saveAndLoad.loadTasks(fileName);
    }

    /**
     * Returns the response to the user input.
     *
     * @param input
     * @return The response to the user input.
     */
    public String getResponse(String input) {
        if (input.equals("")) {
            return UserInterface.displayNoStringMessage();
        }
        String[] inputAsList = input.split(" ");
        switch (inputAsList[0]) {
        case "bye":
            saveAndLoad.saveTasks(this.taskList);
            return UserInterface.getExitString();
        case "list":
            return ui.printTaskList();
        case "mark": case "unmark": case "delete":
            return processTasks.markOrDelete(input);
        case "find":
            return processTasks.findTask(input, this.taskList);
        case "load":
            String str = saveAndLoad.loadTasksFromArchiveFile(input);
            this.taskCount = this.taskList.size();
            return str;
        case "archive":
            assert inputAsList.length == 1 : "Invalid archive command";
            this.taskList = saveAndLoad.archiveTasks(this.taskList);
            this.taskCount = 0;
            return UserInterface.displayTaskArchivedMessage();

        default:
            return processTasks.processUserInput(input);
        }
    }
}
