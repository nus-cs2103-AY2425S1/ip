package commands;

import ui.Ui;
import main.TaskList;
import tasks.Task;
import tasks.Deadline;
import storage.Storage;

import java.time.LocalDateTime;

/**
 * Represents a command to create a deadline task.
 * This command handles the creation of a task that has a specific deadline.
 */
public class DeadlineCommand extends Command {
    private final String taskDescription;
    private final String priority;
    private final LocalDateTime dueWhen;

    /**
     * Constructs a {@code DeadlineCommand} with the specified task description and due date/time.
     *
     * @param taskDescription the description of the deadline task
     * @param dueWhen the due date and time of the task
     */
    public DeadlineCommand(String taskDescription, String priority, LocalDateTime dueWhen) {
        this.taskDescription = taskDescription;
        this.priority = priority;
        this.dueWhen = dueWhen;
    }

    /**
     * Executes the deadline command by creating a new {@code Deadline} task and adding it to the task list.
     * Displays appropriate messages to the user and saves the updated task list to storage.
     *
     * @param taskList the list of tasks to add the new deadline task to
     * @param ui the user interface for interacting with the user
     * @param storage the storage handler for saving the updated task list
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.taskDescription == null || this.taskDescription.trim().isEmpty()) {
            ui.showError("deadline WHAT???");
        } else if (this.dueWhen == null) {
            ui.showError("WHEN IS THE DEADLINE???\nPlease use yyyy-MM-dd HHmm format.");
        } else {
            Task newTask = new Deadline(this.taskDescription, this.priority, this.dueWhen);
            taskList.addTask(newTask);
            int numTasks = taskList.size();
            ui.showMessage("Got it. I've added this task:\n" + "  " + newTask.toString() + "\nNow you have " + numTasks + " tasks in the list");
            storage.saveTasks(taskList.getTasks());
        }
    }
}