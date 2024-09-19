package colress.command;

import java.time.LocalDate;

import colress.Parser;
import colress.TaskList;
import colress.TaskType;
import colress.Ui;

/**
 * Represents the date command that prints tasks from the list of tasks that fall on a specific date.
 */
public final class DateCommand extends ListCommand {
    private LocalDate date;
    public DateCommand() {
        super();
    }

    @Override
    public String start(Ui ui, TaskList taskList) {
        return ui.promptDate(TaskType.TODO, taskList);
    }

    public void initialise(LocalDate input) {
        this.date = input;
    }

    /**
     * Facilitates listing all tasks in the provided TaskList object that fall on a specified date, using the provided
     * Ui object to receive date input from the user and to print the output for the user.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.printTasks(taskList, date);
    }

    @Override
    public String toString() {
        return Parser.COMMAND_DATE;
    }
}
