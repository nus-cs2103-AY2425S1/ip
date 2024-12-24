package colress.command;

import colress.ColressUiAdvanced;
import colress.ColressUiBeginner;
import colress.parser.Parser;
import colress.tasklist.TaskList;

/**
 * Represents the toggle command that toggles the mode of the program between Beginner and Advanced.
 */
public class ToggleCommand extends Command {
    public static final String MESSAGE_SUCCESSFUL_EXECUTION = "Sure, I have toggled the mode to %s.";

    public ToggleCommand() {
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
        return String.format(MESSAGE_SUCCESSFUL_EXECUTION, ui.toggleMode() ? "Beginner" : "Advanced");
    }

    @Override
    public String execute(ColressUiAdvanced ui, TaskList taskList) {
        return String.format(MESSAGE_SUCCESSFUL_EXECUTION, ui.toggleMode() ? "Beginner" : "Advanced");
    }

    @Override
    public String toString() {
        return Parser.COMMAND_TOGGLE;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof ToggleCommand;
    }
}
