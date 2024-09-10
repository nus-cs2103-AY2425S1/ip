package lawrence.command;

import lawrence.database.TaskFileManager;
import lawrence.task.TaskList;
import lawrence.ui.UserInterface;

/**
 * Represents the user command to exit the chatbot.
 */
public class ExitSessionCommand extends Command {
    /**
     * Default constructor.
     */
    public ExitSessionCommand(CommandType type) {
       super(type);
    }

    /**
     * Displays an exit message to the user.
     *
     * @param tasks a list of tasks the command may operate
     *              on
     * @param manager a {@link TaskFileManager} instance that
     *                the command may use when saving changes
     *                made
     * @param ui a {@link UserInterface} instance to display
     *           possible messages to the user
     */
    @Override
    public void execute(TaskList tasks, TaskFileManager manager, UserInterface ui) {
        this.response = "That's all folks! Hope to see you again soon!";
    }

    /**
     * Returns a boolean indicating whether the program should
     * continue running after this command. Defaults to false.
     *
     * @return a boolean indicating whether the program should
     *         continue running; always false
     */
    @Override
    public boolean shouldContinue() {
        return false;
    }
}
