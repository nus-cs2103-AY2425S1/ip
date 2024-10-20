package darwin.command;

import darwin.task.TaskManager;
import darwin.ui.Ui;

/**
 * ExitCommand class to handle the exit command.
 */
public class ExitCommand extends Command {
    public static final String CMD_WORD = "bye";
    private static final String END_MSG = "Bye. Hope to see you again soon!";

    /**
     * Executes the exit command.
     *
     * @param taskManager TaskManager object to interact with the task list.
     * @param ui Ui object to interact with the user.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.send(END_MSG);
    }

    /**
     * Returns true to indicate that the program should exit.
     *
     * @return true to indicate that the program should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
