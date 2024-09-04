package colress.command;

import java.time.LocalDate;
import java.time.LocalTime;

import colress.TaskList;
import colress.TaskType;
import colress.Ui;

/**
 * Represents the delete command that removes a task from the list of tasks.
 */
public final class DeleteCommand extends Command {
    private int taskNumber;
    public DeleteCommand() {
        super("Got it. I have removed the task from your list.");
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
     * Facilitates removing a task from the provided TaskList, using the provided Ui object to receive input
     * from the user regarding which task to remove.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        taskList.deleteTask(taskNumber);
        return ui.printConfirmationMessage(taskList, getSuccessfulExecutionMessage());
    }
}
