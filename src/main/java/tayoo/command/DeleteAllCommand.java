package tayoo.command;

import tayoo.Storage;
import tayoo.Tasklist;
import tayoo.Ui;
import tayoo.exception.TayooException;

/**
 * The DeleteAll class contains all the sub-commands that should be executed when a Delete All command is given by
 * the user
 */
public class DeleteAllCommand extends Command {

    public DeleteAllCommand() {
    }

    /**
     * Executes the delete all command, removes all the tasks in the tasklist and tasks are not recoverable.
     *
     * @param tasklist that stores all the tasks.
     * @param ui ui representing the I/O system with the user.
     * @param storage represents the .txt file in which the tasks are stored
     * @throws TayooException if there is any error with executing the command (i.e. invalid input)
     */
    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        if (tasklist.deleteAll()) {
            ui.printText("Deleting all tasks");
        } else {
            ui.printText("No tasks to delete");
        }
    }

    /**
     * Executes the delete all command, removes all the tasks in the tasklist and tasks are not recoverable.
     *
     * @param tasklist that stores all the tasks.
     * @param ui ui representing the I/O system with the user.
     * @param storage represents the .txt file in which the tasks are stored
     * @return String response of the chatbot after executing this DeleteAllCommand
     * @throws TayooException if there is any error with executing the command (i.e. invalid input)
     */
    @Override
    public String guiExecute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        if (tasklist.deleteAll()) {
            storage.deleteWholeTxt();
            return "Deleting all tasks";
        } else {
            return "No tasks to delete";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
