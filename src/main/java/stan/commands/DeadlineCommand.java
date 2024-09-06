package stan.commands;

import stan.Storage;
import stan.TaskList;
import stan.exceptions.StanInvalidArgumentException;
import stan.exceptions.StanInvalidDateTimeFormatException;
import stan.exceptions.StanMissingArgumentException;
import stan.tasks.Deadline;
import stan.tasks.Task;
import stan.ui.Ui;



/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Constructs a DeadlineCommand.
     *
     * @param words The user input split into words.
     * @throws StanMissingArgumentException If the description or '/by' clause is missing.
     * @throws StanInvalidArgumentException If the description or time is empty, or if '/by' clause is missing.
     */
    public DeadlineCommand(String[] words) throws StanMissingArgumentException, StanInvalidArgumentException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new StanMissingArgumentException("The description of a deadline cannot be empty.");
        }

        if (!words[1].contains("/by")) {
            throw new StanInvalidArgumentException("The deadline description is present but the '/by' "
                    + "clause is missing. Please add the '/by' clause followed by the time.");
        }

        String[] parts = words[1].split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new StanInvalidArgumentException("The deadline description and time cannot be empty.");
        }

        this.description = parts[0];
        this.by = parts[1];
    }

    /**
     * Executes the command to add a deadline task to the task list.
     *
     * @param tasks The task list where the task will be added.
     * @param ui The UI object to display feedback to the user.
     * @param storage The storage object to save the updated task list.
     * @throws StanInvalidDateTimeFormatException If the date and time format is incorrect.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws StanInvalidDateTimeFormatException {
        Task task = new Deadline(description, by);
        tasks.add(task);
        storage.saveTasks(tasks.getTasks());
        return ui.showTaskAdded(task, tasks.size());
    }
}
