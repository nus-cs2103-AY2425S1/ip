package commands;

import main.TaskList;
import ui.Ui;
import tasks.Task;
import tasks.Event;
import storage.Storage;

import java.time.LocalDateTime;

/**
 * Represents a command to create an event task.
 * This command handles the creation of a task that has a specific start and end time.
 */
public class EventCommand extends Command {
    private final String taskDescription;
    private final String priority;
    private final LocalDateTime startWhen;
    private final LocalDateTime endWhen;

    /**
     * Constructs an {@code EventCommand} with the specified task description, start time, and end time.
     *
     * @param taskDescription the description of the event task
     * @param startWhen the start time of the event
     * @param endWhen the end time of the event
     */
    public EventCommand(String taskDescription, String priority, LocalDateTime startWhen, LocalDateTime endWhen) {
        this.taskDescription = taskDescription;
        this.priority = priority;
        this.startWhen = startWhen;
        this.endWhen = endWhen;
    }

    /**
     * Executes the event command by creating a new {@code Event} task and adding it to the task list.
     * Displays appropriate messages to the user and saves the updated task list to storage.
     *
     * @param taskList the list of tasks to add the new event task to
     * @param ui the user interface for interacting with the user
     * @param storage the storage handler for saving the updated task list
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.taskDescription == null || this.taskDescription.trim().isEmpty()) {
            ui.showError("deadline WHAT???");
        } else if (this.startWhen == null) {
            ui.showError("WHEN DOES IT START???\nPlease use yyyy-MM-dd HHmm format.");
        } else if (this.endWhen == null) {
            ui.showError("WHEN DOES IT END???\nPlease use yyyy-MM-dd HHmm format.");
        } else {
            Task newTask = new Event(this.taskDescription, this.priority, this.startWhen, this.endWhen);
            taskList.addTask(newTask);
            int numTasks = taskList.size();

            ui.showMessage("Got it. I've added this task:\n" + "  " + newTask.toString() + "\nNow you have " + numTasks + " tasks in the list");
            storage.saveTasks(taskList.getTasks());
        }
    }
}