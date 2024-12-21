package colress.command;

import java.time.LocalDate;
import java.time.LocalTime;

import colress.Parser;
import colress.TaskList;
import colress.TaskType;
import colress.Ui;
import colress.UiAdvanced;
import colress.UiBeginner;

/**
 * Represents the check command that marks a task in the list of tasks as not done.
 */
public final class UncheckCommand extends Command {
    public static final String COMMAND_FORMAT = "uncheck NUMBERS";
    public static final int EXPECTED_ARG_NUMBER = 1;
    private int[] taskNumbers;

    public UncheckCommand() {
        super("Splendid! I have marked this task on your list as not done:");
    }

    public UncheckCommand(String[] arguments) {
        super("Splendid! I have marked this task on your list as not done:", arguments);
    }

    @Override
    public String start(UiBeginner ui, TaskList taskList) {
        return ui.promptTaskNumber(taskList);
    }

    @Override
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
     * Facilitates marking a task in the provided TaskList as not done, using the provided Ui object to receive input
     * from the user regarding which task to mark.
     */
    @Override
    public String execute(UiBeginner ui, TaskList taskList) {
        return ui.printConfirmationMessage(taskList,
                    getSuccessfulExecutionMessage() + taskList.uncheckTask(taskNumbers));
    }

    @Override
    public String execute(UiAdvanced ui, TaskList taskList) {
        String[] args = getArguments();
        checkNumberOfArgs(args, EXPECTED_ARG_NUMBER, COMMAND_FORMAT);
        try {
            ui.parseTaskNumbers(args[0], taskList);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.setCommandType("error");
            return Ui.MESSAGE_NOT_A_VALID_NUMBER_ERROR;
        }
        return ui.printConfirmationMessage(taskList,
                getSuccessfulExecutionMessage() + taskList.uncheckTask(taskNumbers));
    }

    @Override
    public String toString() {
        return Parser.COMMAND_UNCHECK;
    }
}
