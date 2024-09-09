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
     * @param keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
        assert this.keyword != null : "Keyword cannot be null";
    }

    @Override
    public void execute(TaskList tl, Gui ui, Storage st) {
        assert tl != null;
        ui.addMessage(tl.findString(keyword));
    }
}
