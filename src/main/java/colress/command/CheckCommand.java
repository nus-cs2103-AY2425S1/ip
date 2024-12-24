package colress.command;

import java.util.Arrays;

import colress.Ui;
import colress.ColressUiAdvanced;
import colress.ColressUiBeginner;
import colress.exception.InvalidCommandFormatException;
import colress.parser.Parser;
import colress.tasklist.TaskList;

/**
 * Represents the check command that marks a task in the list of tasks as done.
 */
public final class CheckCommand extends Command {
    public static final String MESSAGE_SUCCESSFUL_EXECUTION = "Splendid! I have marked this task on your list as done:";
    public static final String COMMAND_FORMAT = "check NUMBERS";
    public static final int EXPECTED_ARG_NUMBER = 1;
    private int[] taskNumbers;

    public CheckCommand() {
        super();
    }

    public CheckCommand(String[] arguments) {
        super(arguments);
    }

    /**
     * Constructs a CheckCommand with the given fields.
     */
    public CheckCommand(String[] arguments, int[] taskNumbers) {
        super(arguments);
        this.taskNumbers = taskNumbers;
    }

    @Override
    public void initialise(int... input) {
        this.taskNumbers = input;
    }

    @Override
    public String start(ColressUiBeginner ui, TaskList taskList) {
        return ui.promptTaskNumber(taskList);
    }

    /**
     * Facilitates marking a task in the provided TaskList as done, using the provided Ui object to receive input
     * from the user regarding which task to mark.
     */
    @Override
    public String execute(ColressUiBeginner ui, TaskList taskList) {
        return ui.printConfirmationMessage(taskList, MESSAGE_SUCCESSFUL_EXECUTION + taskList.checkTask(taskNumbers));
    }

    @Override
    public String execute(ColressUiAdvanced ui, TaskList taskList) throws InvalidCommandFormatException {
        String[] args = getArguments();
        checkNumberOfArgs(args, EXPECTED_ARG_NUMBER, COMMAND_FORMAT);
        try {
            ui.parseTaskNumbers(args[0], taskList);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.setCommandType("error");
            return Ui.MESSAGE_NOT_A_VALID_NUMBER_ERROR;
        }
        return ui.printConfirmationMessage(taskList, MESSAGE_SUCCESSFUL_EXECUTION + taskList.checkTask(taskNumbers));
    }

    @Override
    public String toString() {
        return Parser.COMMAND_CHECK;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CheckCommand)) {
            return false;
        }

        CheckCommand otherCheckCommand = (CheckCommand) other;
        return Arrays.equals(getArguments(), otherCheckCommand.getArguments())
                && Arrays.equals(taskNumbers, otherCheckCommand.taskNumbers);
    }
}
