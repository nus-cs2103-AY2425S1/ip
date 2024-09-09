package revir.user.command;

import revir.tasks.TaskList;
import revir.user.ui.Ui;

/**
 * Represents a Nop command.
 *
 * This command does nothing and is used as a placeholder.
 * It can be used to represent a command that should exit the program.
 */
public class Nop extends Command {
    /**
     * Creates a new Nop command.
     *
     * @param isExit true if the command should exit the program, false otherwise
     */
    public Nop(boolean isExit) {
        super(isExit);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        return;
    }
}
