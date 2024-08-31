package Commands;

import Task.TaskList;

/**
 * Represents a command to exit the application.
 * This class extends the {@link Command} class and handles the termination of the user's session.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command, signaling the end of the application session.
     * This method prints a farewell message to the user.
     *
     * @param taskList The task list currently in use (not utilized in this command).
     */
    @Override
    public void execute(TaskList taskList) {
        System.out.println("----------------\n" +
                "Till we meet again, GOODBYE!");
    }
}
