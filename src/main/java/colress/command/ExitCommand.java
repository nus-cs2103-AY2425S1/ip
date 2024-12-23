package colress.command;

import java.time.LocalDate;
import java.time.LocalTime;

import colress.TaskType;
import colress.UiAdvanced;
import colress.UiBeginner;
import colress.parser.Parser;
import colress.tasklist.TaskList;
import javafx.application.Platform;

/**
 * Represents the exit command that exits the program.
 */
public final class ExitCommand extends Command {

    public ExitCommand() {
        super("Well then, I hope to see you again sometime.");
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
        Platform.exit();
        return getSuccessfulExecutionMessage();
    }

    @Override
    public String execute(UiAdvanced ui, TaskList taskList) {
        Platform.exit();
        return getSuccessfulExecutionMessage();
    }

    @Override
    public String toString() {
        return Parser.COMMAND_EXIT;
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
