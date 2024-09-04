package colress.command;

import java.time.LocalDate;
import java.time.LocalTime;

import colress.TaskList;
import colress.TaskType;
import colress.Ui;

/**
 * Represents the check command that marks a task in the list of tasks as not done.
 */
public final class UncheckCommand extends Command {
    private int taskNumber;
    public UncheckCommand() {
        super("Right. I have marked this task on your list as not done:\n");
    }

    @Override
    public String start(Ui ui, TaskList taskList) {
        return ui.promptTaskNumber(taskList);
    }

    public void initialise(int input) {
        this.taskNumber = input;
    }

    public void initialise(String input) {}
    public void initialise(TaskType input) {}
    public void initialise(LocalDate input) {}
    public void initialise(LocalTime input) {}

    /**
     * Facilitates marking a task in the provided TaskList as not done, using the provided Ui object to receive input
     * from the user regarding which task to mark.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.printConfirmationMessage(taskList,
                    getSuccessfulExecutionMessage() + taskList.uncheckTask(taskNumber));
    }
}
