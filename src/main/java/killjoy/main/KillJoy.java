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
        taskList.add(new Event(description, from, to));
        this.increaseTaskCount();
    }

    public Task getTask(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    /**
     * Removes a task from the task list.
     *
     * @param taskIndex
     */
    public void removeTask(int taskIndex) {
        this.taskList.remove(taskIndex);
        this.decreaseTaskCount();
    }

    /**
     * Loads tasks from the save file.
     */
    public void loadTasks() {
        saveAndLoad.loadTasks();
    }

    /**
     * Returns the response to the user input.
     *
     * @param input
     * @return The response to the user input.
     */
    public String getResponse(String input) {
        if (input.equals("")) {
            return ui.displayNoStringMessage();
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
        default:
            return processTasks.processUserInput(input);
        }
    }
}
