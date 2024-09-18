package colress.command;

import java.time.LocalDate;
import java.time.LocalTime;

import colress.TaskList;
import colress.TaskType;
import colress.Ui;
import javafx.application.Platform;

/**
 * Represents the exit command that exits the program.
 */
public final class ExitCommand extends Command {
    public ExitCommand() {
        super("Well then, I hope to see you again sometime.");
    }

    @Override
    public String start(Ui ui, TaskList taskList) {
        return execute(ui, taskList);
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

    /**
     * Facilitates exiting the program by calling the exit method of the provided Ui object. The provided TaskList
     * object is irrelevant in this method.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        Platform.exit();
        return getSuccessfulExecutionMessage();
    }
}
