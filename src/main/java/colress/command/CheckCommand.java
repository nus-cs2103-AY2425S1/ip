package colress.command;

import java.time.LocalDate;
import java.time.LocalTime;

import colress.Parser;
import colress.TaskList;
import colress.TaskType;
import colress.Ui;

/**
 * Represents the check command that marks a task in the list of tasks as done.
 */
public final class CheckCommand extends Command {
    private int[] taskNumbers;
    public CheckCommand() {
        super("Splendid! I have marked this task on your list as done:");
    }

    @Override
    public String start(Ui ui, TaskList taskList) {
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
     * Facilitates marking a task in the provided TaskList as done, using the provided Ui object to receive input from
     * the user regarding which task to mark.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.printConfirmationMessage(taskList,
                getSuccessfulExecutionMessage() + taskList.checkTask(taskNumbers));
    }

    @Override
    public String toString() {
        return Parser.COMMAND_CHECK;
    }
}
