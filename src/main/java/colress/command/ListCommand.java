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
 * Represents the list command that prints all tasks from the list of tasks.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super("");
    }

    @Override
    public String start(UiBeginner ui, TaskList taskList) {
        return execute(ui, taskList);
    }

    /**
     * Facilitates listing all tasks in the provided TaskList object, and using the provided Ui object to print the
     * output for the user.
     */
    @Override
    public String execute(UiBeginner ui, TaskList taskList) {
        return ui.printTasks(taskList);
    }

    @Override
    public String execute(UiAdvanced ui, TaskList taskList, String[] args) {
        return ui.printTasks(taskList);
    }

    @Override
    public String toString() {
        return Parser.COMMAND_LIST;
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
}
