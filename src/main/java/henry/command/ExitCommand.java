package henry.command;

import henry.Ui;
import henry.TaskList;
import henry.Storage;
import henry.HenryException;

/**
 * Deals with exiting the chat
 */
public class ExitCommand extends Command {

    @Override
    public boolean isExit(){
        return true;
    }

    /**
     * Ends the chat
     *
     * @param taskList instance of a TaskList class that contains
     *                 an array of tasks
     * @param ui instance of a Ui class that interacts with the user
     * @param storage instance of a storage that contains tasks
     *                recorded previously
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws HenryException {
        System.out.println();
    }
}
