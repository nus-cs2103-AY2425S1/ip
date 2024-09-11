package commands;

import main.TaskList;
import ui.Ui;
import tasks.Task;
import tasks.ToDo;
import storage.Storage;

/**
 * Represents a command to create a to-do task.
 * This command handles the creation of a task without any date or time attached to it.
 */
public class ToDoCommand extends Command {
    private final String taskDescription;
    private final String priority;

    /**
     * Constructs a {@code ToDoCommand} with the specified task description.
     *
     * @param taskDescription the description of the to-do task
     */
    public ToDoCommand(String taskDescription, String priority) {
        this.taskDescription = taskDescription;
        this.priority = priority;
    }

    /**
     * Executes the to-do command by creating a new {@code ToDo} task and adding it to the task list.
     * Displays appropriate messages to the user and saves the updated task list to storage.
     *
     * @param taskList the list of tasks to add the new to task to
     * @param ui the user interface for interacting with the user
     * @param storage the storage handler for saving the updated task list
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.taskDescription == null || this.taskDescription.trim().isEmpty()) {
            ui.showError("todo WHAT????");
        } else {
            Task newTask = new ToDo(this.taskDescription, this.priority);
            taskList.addTask(newTask);
            int numTasks = taskList.size();

            ui.showMessage("Got it. I've added this task:" + "\n  " + newTask.toString() + "\nNow you have " + numTasks + " tasks in the list");
            storage.saveTasks(taskList.getTasks());
        }
    }
}
