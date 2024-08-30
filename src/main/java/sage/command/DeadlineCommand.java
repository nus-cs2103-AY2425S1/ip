package sage.command;

import sage.exception.SageException;
import sage.ui.Ui;
import sage.storage.Storage;
import sage.task.Task;
import sage.task.TaskList;
import sage.task.Deadline;

/**
 * Represents a command to add a deadline task to the task list
 */
public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs a DeadlineCommand object by parsing the user's input
     * The input string should contain a description of the task,
     * followed by the deadline in the format:
     * description /by deadline
     *
     * @param input The description and deadline of task
     * @throws SageException If the input is not in the correct format
     */
    public DeadlineCommand(String input) throws SageException {
        String[] parts = input.split(" /by ");
        if (parts.length < 2) {
            throw new SageException("You need to add a task and a deadline!! -_-");
        }
        this.description = parts[0].trim();
        this.by = parts[1].trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SageException {
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        ui.showMessage("Great! I will add this task to the list:\n" + task +
                "\nNow you have " + tasks.size() +
                (tasks.size() > 1 ? " tasks" : " task") + " in your list");
        storage.saveTasks(tasks);
    }
}
