package mittens.commands;

import java.time.LocalDate;

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
 * Represents a command for deleting a task from the task list.
 */
public class DeleteCommand extends Command {
    protected int index;

    /**
     * Creates a new DeleteCommand object with the specified command elements.
     * It assumes the command name corresponds with this class.
     *
     * @param elements The RawCommandElements object
     * @throws BadInputException If the input is invalid
     */
    public DeleteCommand(RawCommandElements elements) throws BadInputException {
        assert elements.getCommand().equals("delete") : "Command name should be matching";
        this.index = elements.getArgumentAsIntegerOrThrow();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task deletedTask = tasks.deleteTask(this.index - 1);

            storage.save(tasks);

            ui.showRegularMessage("Meow, I deleted the task \"%s\" for you :3"
                    .formatted(deletedTask.getDescription()));
        } catch (MittensException e) {
            ui.showErrorMessage(e);
        }
    }
}
