package colress.command;

import java.time.LocalDate;
import java.time.LocalTime;

import colress.TaskList;
import colress.TaskType;
import colress.UiAdvanced;
import colress.UiBeginner;
import colress.exception.InvalidCommandFormatException;

/**
 * Encapsulates the behaviour of a command.
 */
public abstract class Command {
    private final String successfulExecutionMessage;

    public Command(String successfulExecutionMessage) {
        this.successfulExecutionMessage = successfulExecutionMessage;
    }

    public String getSuccessfulExecutionMessage() {
        return successfulExecutionMessage;
    }

    public void checkNumberOfArgs(String[] args, int expectedArgNumber, String invalidCommandFormatMessage)
            throws InvalidCommandFormatException {
        if (args.length != expectedArgNumber) {
            throw new InvalidCommandFormatException(invalidCommandFormatMessage);
        }
    }

    public abstract String start(UiBeginner ui, TaskList taskList);
    public abstract void initialise(String input);
    public abstract void initialise(TaskType input);
    public abstract void initialise(int... input);
    public abstract void initialise(LocalDate input);
    public abstract void initialise(LocalTime input);

    public abstract String execute(UiBeginner ui, TaskList taskList);
    public abstract String execute(UiAdvanced ui, TaskList taskList, String[] arguments);
}
