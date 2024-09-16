package tayoo.command;

import tayoo.Storage;
import tayoo.Tasklist;
import tayoo.Ui;
import tayoo.exception.TayooException;

/**
 * The ListCommand class contains all the sub-commands that should be executed when a List command is given by
 * the user
 */
public class ListCommand extends Command {

    public ListCommand() {
    }

    /**
     * Executes the list command and prints out all the tasks that are currently in the list
     *
     * @param tasklist that stores all the tasks.
     * @param ui ui representing the I/O system with the user.
     * @param storage represents the .txt file in which the tasks are stored
     * @throws TayooException if there is any error with executing the command (i.e. invalid input)
     */
    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        ui.printText(tasklist.printTaskList());
    }

    /**
     * Executes the list command and returns a string representation of all the tasks that are currently in the list.
     *
     * @param tasklist that stores all the tasks.
     * @param ui ui representing the I/O system with the user.
     * @param storage represents the .txt file in which the tasks are stored
     * @return String response of the chatbot after executing this ListCommand
     * @throws TayooException if there is any error with executing the command (i.e. invalid input)
     */
    @Override
    public String guiExecute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        return tasklist.printTaskList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
