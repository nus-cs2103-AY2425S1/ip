package vuewee.ui;

import javafx.application.Application;
import javafx.application.Platform;
import vuewee.EndProgramException;
import vuewee.command.Command;
import vuewee.command.CommandType;
import vuewee.parser.CommandParser;
import vuewee.parser.IllegalCommandException;
import vuewee.task.Task;
import vuewee.task.TaskList;
import vuewee.task.TaskLocalDate;
import vuewee.ui.gui.VueweeGui;

/**
 * The TaskListGui class represents the user interface for Vuewee. It contains
 * methods to add, delete, display, and mark tasks as done or not done.
 */
public class TaskListGui extends TaskListUi {
    private static TaskListGui instance;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private TaskListGui() {
        super();
    }

    /**
     * Returns the singleton instance of TaskListGui.
     *
     * @return The singleton instance
     */
    public static TaskListGui getInstance() {
        if (instance == null) {
            instance = new TaskListGui();
        }
        return instance;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added
     */
    @Override
    public void addTask(Task task) {
        super.addTask(task);
        VueweeGui.sendMessage("Got it. I've added this task:\n " + task.toString() + "\nNow you have " + super.taskList
                .size() + " " + this.taskWord() + " in the list.");
    }

    /**
     * Delete a task from the list
     *
     * @param taskNumber The index of the task to be deleted (1-based)
     * @throws IndexOutOfBoundsException
     */
    @Override
    public Task deleteTask(int taskNumber) throws IndexOutOfBoundsException {
        Task deletedTask = super.deleteTask(taskNumber);
        VueweeGui.sendMessage("Noted. I've removed this task:\n " + deletedTask.toString() + "\nNow you have "
                + this.taskList.size() + " " + super.taskWord() + " in the list.");

        return deletedTask;
    }

    /**
     * Display all tasks in the list that match the keyword. Search is done by
     * another method that returns a new TaskList with matching tasks.
     *
     * @param tasks TaskList to search for matching tasks
     * @throws IllegalCommandException
     */
    @Override
    public void displayTasks(TaskList tasks) throws IllegalCommandException {
        if (tasks.size() == 0) {
            throw new IllegalCommandException("No tasks found.");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            sb.append("  ").append(i + 1).append(". ").append(task.toString()).append("\n");
        }
        VueweeGui.sendMessage(sb.toString());
    }

    @Override
    public void displaySchedule(TaskLocalDate date) {
        TaskList tasks = this.taskList.getTasksOnDate(date);
        if (tasks.size() == 0) {
            VueweeGui.sendMessage("No tasks scheduled for " + date.toString());
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks scheduled for " + date.toString() + ":\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            sb.append("  " + (i + 1) + ". " + task.toString() + "\n");
        }
        VueweeGui.sendMessage(sb.toString());
    }

    /**
     * Mark a task as done or not done
     *
     * @param taskNumber The index of the task to be marked (1-based)
     * @param isDone     The new status of the task
     * @throws IllegalCommandException
     */
    @Override
    public void markTask(int taskNumber, boolean isDone) throws IllegalCommandException {
        try {
            super.markTask(taskNumber, isDone);
            if (isDone) {
                VueweeGui.sendMessage("Nice! I've marked this task as done:\n  " + this.taskList.get(taskNumber - 1)
                        .toString());
            } else {
                VueweeGui.sendMessage("OK, I've marked this task as not done yet:\n  " + this.taskList.get(taskNumber
                        - 1).toString());
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalCommandException("Invalid task number. There are " + this.taskList.size() + " " + this
                    .taskWord() + " in your list.");
        }
    }

    /**
     * Processes user input from GUI and executes the corresponding command.
     *
     * @param userInput User input
     */
    public void processInput(String userInput) {
        try {
            CommandParser parser = new CommandParser(userInput);
            CommandType commandType = parser.getCommandType();
            Command command = commandType.createCommand();
            command.executeCommand(this, taskList, parser);
        } catch (IndexOutOfBoundsException | IllegalCommandException e) {
            VueweeGui.sendMessage(e.getMessage());
        } catch (EndProgramException e) {
            VueweeGui.sendMessage("Bye. Hope to see you again soon!");
            this.close();
            Platform.exit();
            System.exit(0);
        }
        storage.storeTasks(this.taskList);
    }

    /**
     * Starts the Vuewee program and reads user input until the user types "bye".
     */
    @Override
    public void run() {
        this.taskList = this.storage.readTasks();
        VueweeGui.setTaskListGui(this);
        Application.launch(VueweeGui.class);
    }

    @Override
    public void close() {
        TaskListGui.instance = null;
        this.storage.storeTasks(this.taskList);
    }
}
