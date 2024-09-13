package topaz.command;

import java.util.Objects;

import topaz.exception.InvalidCommandException;
import topaz.main.Storage;
import topaz.main.TaskList;
import topaz.ui.Ui;

/**
 * Represents commands that will only return text when executed, has no effect on taskList and file.
 * Currently, includes 'help', 'bye' and 'list'
 */
public class TextCommand extends Command {

    /**
     * Constructs a TextCommand with the specified keyword.
     * If the keyword is "bye", the command will set the exit flag to true, indicating the termination of the program.
     *
     * @param keyword The keyword specifying the type of text command (e.g., "help", "bye", or "list").
     */
    public TextCommand(String keyword) {
        super(keyword);
        if (Objects.equals(keyword, "bye")) {
            super.isExit = true;
        }
    }

    /**
     * Executes the text command based on the specified keyword.
     * The command produces text output through the Ui and does not modify the TaskList or the storage file.
     *
     * <p>Depending on the keyword, this method performs the following actions:
     * <ul>
     *     <li>"bye": Invokes the {@code Ui.goodbye()} method to display a farewell message and sets the exit flag.</li>
     *     <li>"help": Invokes the {@code Ui.showHelp()} method to display help information.</li>
     *     <li>"list": Invokes the {@code Ui.showTaskList()} method to display the list of tasks and the {@code TaskList.listTasks()} method to list tasks.</li>
     * </ul></p>
     *
     * @param tasks   The TaskList, which is not modified by this command.
     * @param ui      The Ui used to display text output to the user.
     * @param storage The Storage, which is not used by this command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            switch (super.keyword) {
            case "bye":
                return ui.goodbye();
            case "help":
                return ui.showHelp();
            case "list":
                String listString = tasks.listTasks();
                return ui.showTaskList() + listString;
            default:
                throw new InvalidCommandException(super.keyword);
            }
        } catch (InvalidCommandException e) {
            return ui.showException(e);
        }
    }

}
