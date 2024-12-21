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
 * Represents the delete command that removes a task from the list of tasks.
 */
public final class DeleteCommand extends Command {
    public static final String MESSAGE_INVALID_FORMAT = "What is this?! I do not recognise that command format!"
            + "Here's the correct format: delete NUMBERS";
    public static final int EXPECTED_ARG_NUMBER = 2;
    private int[] taskNumbers;
    public DeleteCommand() {
        super("Splendid! I have removed the task from your list.");
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
        return ui.printConfirmationMessage(taskList, getSuccessfulExecutionMessage());
    }

    @Override
    public String execute(UiAdvanced ui, TaskList taskList, String[] args) {
        checkNumberOfArgs(args, EXPECTED_ARG_NUMBER, MESSAGE_INVALID_FORMAT);
        try {
            ui.parseTaskNumbers(args[1], taskList);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.setCommandType("error");
            return Ui.MESSAGE_NOT_A_VALID_NUMBER_ERROR;
        }
        taskList.deleteTask(taskNumbers);
        return ui.printConfirmationMessage(taskList, getSuccessfulExecutionMessage());
    }

    @Override
    public String toString() {
        return Parser.COMMAND_DELETE;
    }
}
