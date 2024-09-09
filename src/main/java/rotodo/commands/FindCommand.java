package rotodo.commands;

import rotodo.processes.Gui;
import rotodo.processes.Storage;
import rotodo.tasklist.TaskList;

/**
 * The FindCommand class encapsulates the specific
 * type of Command that executes a find action.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Initialise FindCommand to be executed. Accepts
     * a keyword to be used to find tasks.
     *
<<<<<<< HEAD
     * @param keyword to search
=======
     * @param keyword
>>>>>>> master
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
        assert this.keyword != null : "Keyword cannot be null";
    }

    @Override
    public void execute(TaskList tasks, Gui gui, Storage storage) {
        assert tasks != null;
        gui.addMessage(tasks.findString(keyword));
    }
}
