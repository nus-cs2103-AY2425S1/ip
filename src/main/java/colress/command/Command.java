package colress.command;

import java.time.LocalDate;
import java.time.LocalTime;

import colress.TaskType;
import colress.ColressUiAdvanced;
import colress.ColressUiBeginner;
import colress.exception.InvalidCommandFormatException;
import colress.tasklist.TaskList;

/**
 * Encapsulates the behaviour of a command.
 */
public abstract class Command {
    public static final String MESSAGE_INVALID_FORMAT = "What is this?! I do not recognise that command format!\n"
            + "Here's the correct format: %s";
    private String[] arguments;

    public Command() {}

    /**
     * Constructs a command with its arguments.
     */
    public Command(String[] arguments) {
        this.arguments = arguments;
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

    public void initialise(LocalDate input) {
    };
    public void initialise(LocalTime input) {
    };
    public void initialise(String input) {
    };
    public void initialise(TaskType input) {
    };
    public void initialise(int... input) {
    };

    public abstract String start(ColressUiBeginner ui, TaskList taskList);
    public abstract String execute(ColressUiBeginner ui, TaskList taskList);
    public abstract String execute(ColressUiAdvanced ui, TaskList taskList) throws InvalidCommandFormatException;
}
