package mittens.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import mittens.MittensException;
import mittens.parser.BadInputException;
import mittens.parser.RawCommandElements;
import mittens.storage.Storage;
import mittens.task.Deadline;
import mittens.task.Event;
import mittens.task.Task;
import mittens.task.TaskList;
import mittens.task.Todo;
import mittens.ui.Ui;

/**
 * Represents a command for adding a task into the task list.
 */
public class AddCommand extends Command {
    protected Task toAdd;

    /**
     * Creates a new AddCommand object with the specified task to add.
     * 
     * @param toAdd The task to add
     */
    public AddCommand(Task toAdd) {
        super();
        this.toAdd = toAdd;
    }

    /**
     * Creates a new AddCommand object with the specified command elements.
     * It assumes the command name corresponds with this class.
     *
     * @param elements The RawCommandElements object
     * @throws BadInputException If the input is invalid
     */
    public AddCommand(RawCommandElements elements) throws BadInputException {
        String taskType = elements.getCommand();
        String description = elements.getArgumentOrThrow();

        switch (taskType) {
        case "todo":
            this.toAdd = new Todo(description);
            break;

        case "deadline":
            LocalDate by = elements.getFlagAsDateOrThrow("by");
            this.toAdd = new Deadline(description, by);
            break;

        case "event":
            LocalDate from = elements.getFlagAsDateOrThrow("from");
            LocalDate to = elements.getFlagAsDateOrThrow("to");
            if (from.isAfter(to)) {
                throw new BadInputException("The start date cannot be after the end date");
            }
            this.toAdd = new Event(description, from, to);
            break;

        default:
            assert false : "Command name should be matching";
            break;
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addTask(this.toAdd);

            storage.save(tasks);

            ui.showRegularMessage("I've added \"%s\" to your list :3"
                    .formatted(this.toAdd.getDescription()));
        } catch (MittensException e) {
            ui.showErrorMessage(e);
        }
    }
}
