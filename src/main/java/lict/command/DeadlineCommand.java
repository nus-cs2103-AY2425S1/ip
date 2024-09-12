package lict.command;

import lict.LictException;
import lict.Storage;
import lict.TaskList;
import lict.Ui;
import lict.task.Deadline;
import lict.task.Task;

/**
 * The {@code DeadlineCommand} class is responsible for handling the addition of a new deadline task.
 * It processes the user input, creates a new {@code Deadline} object, and adds it to the task list.
 * The command also updates the UI and storage with the new task.
 */
public class DeadlineCommand extends Command {
    private String info;

    public DeadlineCommand(String info) {
        this.info = info;
    }

    /**
     * Executes the command to add a new deadline task.
     *
     * @param tasks    The task list to which the new deadline task will be added.
     * @param ui       The UI component responsible for displaying the addition of the new task.
     * @param storage  The storage to save the updated task list to.
     * @throws LictException If the input format is invalid or the description is empty.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LictException {
        String[] messageParts = info.split("/by", 2);
        String description = messageParts[0].trim();
        if (description.isEmpty()) {
            throw new LictException(
                    """
                    OOPS!!! The description of a deadline cannot be empty.
                     Please ensure that your input is in the format: deadline {description} /by {your deadline}"""
            );
        }
        if (messageParts.length != 2) {
            throw new LictException(
                    """
                    OOPS!!! The deadline needs to be indicated.
                     Please ensure that your input is in the format: deadline {description} /by {your deadline}"""
            );
        }
        Task newTask = new Deadline(description, messageParts[1].trim());
        tasks.addTask(newTask);
        ui.hasAddedTask(newTask, tasks.size());
        storage.saveTasks(tasks);
    }
}
