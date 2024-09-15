package lawrence.command;

import lawrence.database.TaskFileManager;
import lawrence.task.TaskList;
import lawrence.ui.UserInterface;

/**
 * Represents the user command to display all existing tasks in the list.
 */
public class DisplayTasksCommand extends Command {
    /**
     * Default constructor.
     */
    public DisplayTasksCommand(CommandType type) {
        super(type);
    }

    /**
     * Displays all tasks present in the {@link TaskList} to the user.
     * If no tasks exist, a different message is displayed.
     *
     * @param tasks a list of tasks the command may operate
     *              on
     * @param manager  a {@link TaskFileManager} instance that
     *                 the command may use when saving changes
     *                 made
     * @param ui a {@link UserInterface} instance to display
     *           possible messages to the user
     * @return a string representing the bot's response after execution of the command
     */
    @Override
    public String execute(TaskList tasks, TaskFileManager manager, UserInterface ui) {
        if (tasks.getSize() < 1) {
            return "You have no tasks at the moment.";
        }

        assert tasks.getSize() >= 0;
        return String.format("Here's your laundry list:%n%s", tasks);
    }
}
