package controllers.commands;

import controllers.OutputHandler;
import models.TaskList;

/**
 * Represents a command to exit the task management system.
 * The {@code ByeCommand} class implements the {@code Command} interface
 * and terminates the program when executed.
 *
 * <p>This command prints a farewell message and exits the application.</p>
 */
public class ByeCommand implements Command {

    /**
     * Executes the command to terminate the program.
     * It prints a farewell message and calls {@code System.exit(0)} to close the application.
     *
     * @param taskList The {@code TaskList}, though not used in this command.
     */
    @Override
    public void execute(TaskList taskList, OutputHandler outputHandler) {
        assert taskList != null : "taskList must not be null";
        assert outputHandler != null : "outputHandler must not be null";
        outputHandler.print(
                "Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________");
        System.exit(0);
    }
}
