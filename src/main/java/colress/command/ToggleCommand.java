package colress.command;

import java.time.LocalDate;
import java.time.LocalTime;

import colress.TaskType;
import colress.UiAdvanced;
import colress.UiBeginner;
import colress.parser.Parser;
import colress.tasklist.TaskList;

/**
 * Represents the toggle command that toggles the mode of the program between Beginner and Advanced.
 */
public class ToggleCommand extends Command {
    public ToggleCommand() {
        super("Sure, I have toggled the mode to %s.");
    }

    @Override
    public String start(UiBeginner ui, TaskList taskList) {
        return execute(ui, taskList);
    }

    /**
     * Facilitates exiting the program by calling the exit method of the provided Ui object. The provided TaskList
     * object is irrelevant in this method.
     */
    @Override
    public String execute(UiBeginner ui, TaskList taskList) {
        return String.format(getSuccessfulExecutionMessage(), ui.toggleMode() ? "Beginner" : "Advanced");
    }

    @Override
    public String execute(UiAdvanced ui, TaskList taskList) {
        return String.format(getSuccessfulExecutionMessage(), ui.toggleMode() ? "Beginner" : "Advanced");
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

    public void initialise(String input) {
    }
    public void initialise(TaskType input) {
    }
    public void initialise(int... input) {
    }
    public void initialise(LocalDate input) {
    }
    public void initialise(LocalTime input) {
    }
}
