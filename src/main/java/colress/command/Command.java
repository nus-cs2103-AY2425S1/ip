package colress.command;

import java.time.LocalDate;
import java.time.LocalTime;

import colress.TaskType;
import colress.UiAdvanced;
import colress.UiBeginner;
import colress.exception.InvalidCommandFormatException;
import colress.tasklist.TaskList;

/**
 * Encapsulates the behaviour of a command.
 */
public abstract class Command {
    public static final String MESSAGE_INVALID_FORMAT = "What is this?! I do not recognise that command format!\n"
            + "Here's the correct format: %s";
    private final String successfulExecutionMessage;
    private String[] arguments;

    public Command(String successfulExecutionMessage) {
        this.successfulExecutionMessage = successfulExecutionMessage;
    }

    /**
     * Constructs a command with its arguments.
     */
    public Command(String successfulExecutionMessage, String[] arguments) {
        this.successfulExecutionMessage = successfulExecutionMessage;
        this.arguments = arguments;
    }

    public String getSuccessfulExecutionMessage() {
        return successfulExecutionMessage;
    }

    public String[] getArguments() {
        return arguments;
    }

    /**
     * Checks if the number of arguments supplied to the command is valid.
     */
    public void checkNumberOfArgs(String[] args, int expectedArgNumber, String commandFormat)
            throws InvalidCommandFormatException {
        if (args == null || args.length != expectedArgNumber) {
            throw new InvalidCommandFormatException(String.format(MESSAGE_INVALID_FORMAT, commandFormat));
        }
    }

    public abstract String start(UiBeginner ui, TaskList taskList);
    public abstract void initialise(String input);
    public abstract void initialise(TaskType input);
    public abstract void initialise(int... input);
    public abstract void initialise(LocalDate input);
    public abstract void initialise(LocalTime input);

    public abstract String execute(UiBeginner ui, TaskList taskList);
    public abstract String execute(UiAdvanced ui, TaskList taskList) throws InvalidCommandFormatException;
}
