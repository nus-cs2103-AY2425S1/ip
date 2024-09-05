package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.ui.TrackieException;
import trackie.ui.Ui;

/**
 * A command that adds a task to <code>TaskList</code>.
 *
 * The type of command that is added is determined by the
 * <code>arguments</code> that are passed in.
 */
public class AddCommand extends Command {

    /**
     * Constructs a new Add Command with the arguments provided by the user.
     *
     * @param arguments An array of Strings storing the arguments provided by the user.
     */
    public AddCommand(String[] arguments) {
        super(false);
        super.arguments = arguments;
    }

    /**
     * Executes the add command.
     * Depending on the first argument that the user provides, this command will
     * tell the task list to add either a todo, deadline or event task.
     * If an exception is thrown in the process of adding the command, its
     * error message will be displayed to the user.
     *
     * @param tasklist The TaskList object to which a task will be added.
     * @param ui The Ui object used to display messages to the user.
     * @param storage The Storage object used to save the updated task list.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            switch (arguments[0]) {
            case "todo":
                tasklist.addTodoTask(arguments);
                break;
            case "deadline":
                tasklist.addDeadlineTask(arguments);
                break;
            case "event":
                tasklist.addEventTask(arguments);
            }
            storage.save();
        } catch (TrackieException e) {
            ui.displayErrorMessage(e);
        }
    }
}
