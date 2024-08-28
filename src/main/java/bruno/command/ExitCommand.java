package bruno.command;

import bruno.task.TaskList;
import bruno.Ui;

/**
 * Represents a command to exit the application.
 * This command sets the application’s exit status to true and displays a farewell message.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand with the specified task list.
     * This constructor also sets the command to exit the application.
     *
     * @param tasks The task list associated with this command (though it is not used in this command).
     */
    public ExitCommand(TaskList tasks) {
        super(tasks);
        super.exit();
    }

    /**
     * Executes the command by printing a farewell message and setting the exit status.
     * This method will terminate the application loop when executed.
     */
    @Override
    public void execute() {
        Ui.printByeMessage();
    }
}