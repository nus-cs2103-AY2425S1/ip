package colress.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import colress.TaskType;
import colress.Ui;
import colress.UiAdvanced;
import colress.UiBeginner;
import colress.exception.InvalidCommandFormatException;
import colress.parser.Parser;
import colress.tasklist.TaskList;

/**
 * Represents the delete command that removes a task from the list of tasks.
 */
public final class DeleteCommand extends Command {
    public static final String MESSAGE_SUCCESSFUL_EXECUTION = "Splendid! I have removed the task from your list.";
    public static final String COMMAND_FORMAT = "delete NUMBERS";
    public static final int EXPECTED_ARG_NUMBER = 1;
    private int[] taskNumbers;

    public DeleteCommand() {
        super();
    }

    public DeleteCommand(String[] arguments) {
        super(arguments);
    }

    /**
     * Constructs a DeleteCommand with the given fields.
     */
    public DeleteCommand(String[] arguments, int[] taskNumbers) {
        super(arguments);
        this.taskNumbers = taskNumbers;
    }

    @Override
    public String start(UiBeginner ui, TaskList taskList) {
        return ui.promptTaskNumber(taskList);
    }

    public void initialise(int... input) {
        this.taskNumbers = input;
    }

    public void initialise(String input) {
    }
    public void initialise(TaskType input) {
    }
    public void initialise(LocalDate input) {
    }
    public void initialise(LocalTime input) {
    }

    /**
     * Facilitates removing a task from the provided TaskList, using the provided Ui object to receive input
     * from the user regarding which task to remove.
     */
    @Override
    public String execute(UiBeginner ui, TaskList taskList) {
        taskList.deleteTask(taskNumbers);
        return ui.printConfirmationMessage(taskList, MESSAGE_SUCCESSFUL_EXECUTION);
    }

    @Override
    public String execute(UiAdvanced ui, TaskList taskList) throws InvalidCommandFormatException {
        String[] args = getArguments();
        checkNumberOfArgs(args, EXPECTED_ARG_NUMBER, COMMAND_FORMAT);
        try {
            ui.parseTaskNumbers(args[0], taskList);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.setCommandType("error");
            return Ui.MESSAGE_NOT_A_VALID_NUMBER_ERROR;
        }
        taskList.deleteTask(taskNumbers);
        return ui.printConfirmationMessage(taskList, MESSAGE_SUCCESSFUL_EXECUTION);
    }

    @Override
    public String toString() {
        return Parser.COMMAND_DELETE;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return Arrays.equals(getArguments(), otherDeleteCommand.getArguments())
                && Arrays.equals(taskNumbers, otherDeleteCommand.taskNumbers);
    }
}
