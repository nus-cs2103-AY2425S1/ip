package colress.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import colress.Parser;
import colress.TaskList;
import colress.TaskType;
import colress.Ui;
import colress.UiAdvanced;
import colress.UiBeginner;

/**
 * Represents the date command that prints tasks from the list of tasks that fall on a specific date.
 */
public final class DateCommand extends ListCommand {
    public static final String MESSAGE_INVALID_FORMAT = "What is this?! I do not recognise that command format!"
            + "Here's the correct format: date DATE";
    public static final int EXPECTED_ARG_NUMBER = 2;
    private LocalDate date;
    public DateCommand() {
        super();
    }

    @Override
    public String start(UiBeginner ui, TaskList taskList) {
        return ui.promptDate(TaskType.TODO, taskList);
    }

    @Override
    public void initialise(LocalDate input) {
        this.date = input;
    }

    /**
     * Facilitates listing all tasks in the provided TaskList object that fall on a specified date, using the provided
     * Ui object to receive date input from the user and to print the output for the user.
     */
    @Override
    public String execute(UiBeginner ui, TaskList taskList) {
        return ui.printTasks(taskList, date);
    }

    @Override
    public String execute(UiAdvanced ui, TaskList taskList, String[] args) {
        checkNumberOfArgs(args, EXPECTED_ARG_NUMBER, MESSAGE_INVALID_FORMAT);
        try {
            ui.parseDate(args[1]);
            return ui.printTasks(taskList, date);
        } catch (DateTimeParseException e) {
            ui.setCommandType("error");
            return Ui.MESSAGE_NOT_A_VALID_DATE_TIME_ERROR;
        }
    }

    @Override
    public String toString() {
        return Parser.COMMAND_DATE;
    }
}
