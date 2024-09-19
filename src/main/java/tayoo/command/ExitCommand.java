package tayoo.command;

import tayoo.Storage;
import tayoo.Tasklist;
import tayoo.Ui;
import tayoo.exception.TayooException;

/**
 * The ExitCommand class contains all the sub-commands that should be executed when an Exit command is given by
 * the user
 */
public class ExitCommand extends Command {

    public ExitCommand() {
    }

    /**
     * Executes the exit command and prints out all the exit message
     *
     * @param tasklist that stores all the tasks.
     * @param ui ui representing the I/O system with the user.
     * @param storage represents the .txt file in which the tasks are stored
     * @throws TayooException if there is any error with executing the command (i.e. invalid input)
     */
    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        ui.showExit();
    }

    /**
     * Executes the exit command and returns the exit message as a string.
     *
     * @param tasklist that stores all the tasks.
     * @param ui ui representing the I/O system with the user.
     * @param storage represents the .txt file in which the tasks are stored
     * @return String response of the chatbot after executing this ExitCommand
     * @throws TayooException if there is any error with executing the command (i.e. invalid input)
     */
    @Override
    public String guiExecute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        return ui.getExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
