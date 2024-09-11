package monobot.command;

import monobot.util.Storage;
import monobot.util.TaskList;
import monobot.util.Ui;

/**
 * Represents invalid command that is not recognised or supported.
 */
public class InvalidCommand extends Command {

    /**
     * Constructs a InvalidCommand to handle commands not recognised.
     */
    public InvalidCommand() {
        super(CommandType.INVALID);
    }

    /**
     * Executes invalid command.
     *
     * @param tasks list of tasks containing task to mark.
     * @param ui ui object to handle output to user.
     * @param storage storage object to read/write file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printError("Invalid command. Valid commands are: \n"
                + "list, todo, deadline, event, mark, unmark, bye");
    }
}
