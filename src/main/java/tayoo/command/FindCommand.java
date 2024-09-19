package tayoo.command;

import tayoo.Storage;
import tayoo.Tasklist;
import tayoo.Ui;
import tayoo.exception.TayooException;

/**
 * The FindCommand class contains all the sub-commands that should be executed when a Find command is given by
 * the user
 */
public class FindCommand extends Command {

    private final String substringToFind;

    /**
     * Creates a new FindCommand instance.
     *
     * @param substring the substring that is to be looked for within the list of tasks' titles
     */
    public FindCommand(String substring) {
        this.substringToFind = substring;
    };

    /**
     * Executes the find command and prints out all the tasks whose titles contain the substring that is to be searched
     * for.
     *
     * @param tasklist that stores all the tasks.
     * @param ui ui representing the I/O system with the user.
     * @param storage represents the .txt file in which the tasks are stored
     * @throws TayooException if there is any error with executing the command (i.e. invalid input)
     */
    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        ui.printText(tasklist.find(substringToFind));
    }

    /**
     * Executes the find command and returns a string representation of all the tasks whose titles contain the substring
     * that is to be searched for
     *
     * @param tasklist that stores all the tasks.
     * @param ui ui representing the I/O system with the user.
     * @param storage represents the .txt file in which the tasks are stored
     * @return String response of the chatbot after executing this FindCommand
     * @throws TayooException if there is any error with executing the command (i.e. invalid input)
     */
    @Override
    public String guiExecute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        return tasklist.find(substringToFind);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
