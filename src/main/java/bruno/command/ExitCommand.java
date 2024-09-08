package bruno.command;

import bruno.Ui;
import bruno.task.TaskList;

/**
 * Represents a command to exit the application.
 * This command sets the application’s exit status to true and displays a farewell message.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand with the specified task list.
     * This constructor also sets the command to exit the application.
     *
     * @param taskList The task list associated with this command (though it is not used in this command).
     */
    public ExitCommand(TaskList taskList) {
        super(taskList);
        super.exit();
    }

    /**
     * Executes the command by printing a farewell message and setting the exit status.
     * This method will terminate the application loop when executed.
     */
    @Override
    public void execute() {

    }

    @Override
    public String toString() {
        return "Bye. Hope to see you again soon!";
    }
}
