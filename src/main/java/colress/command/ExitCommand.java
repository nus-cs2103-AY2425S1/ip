package colress.command;

import colress.ColressUiAdvanced;
import colress.ColressUiBeginner;
import colress.parser.Parser;
import colress.tasklist.TaskList;
import javafx.application.Platform;

/**
 * Represents the exit command that exits the program.
 */
public final class ExitCommand extends Command {
    public static final String MESSAGE_SUCCESSFUL_EXECUTION = "Well then, I hope to see you again sometime.";
    public ExitCommand() {
        super();
    }

    @Override
    public String start(ColressUiBeginner ui, TaskList taskList) {
        return execute(ui, taskList);
    }

    /**
     * Facilitates exiting the program by calling the exit method of the provided Ui object. The provided TaskList
     * object is irrelevant in this method.
     */
    @Override
    public String execute(ColressUiBeginner ui, TaskList taskList) {
        Platform.exit();
        return MESSAGE_SUCCESSFUL_EXECUTION;
    }

    @Override
    public String execute(ColressUiAdvanced ui, TaskList taskList) {
        Platform.exit();
        return MESSAGE_SUCCESSFUL_EXECUTION;
    }

    @Override
    public String toString() {
        return Parser.COMMAND_EXIT;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof ExitCommand;
    }
}
