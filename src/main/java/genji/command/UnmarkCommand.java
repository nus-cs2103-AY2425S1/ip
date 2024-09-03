package genji.command;

import genji.task.TaskList;
import genji.Ui;
import genji.Storage;

/**
 * A class that deals with unmarking command
 */
public class UnmarkCommand extends Command{
    int index;

    /**
     * Constructor of unmarking command
     * @param index Number concerning which task to be unmarked
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Calling the unmark method in task list class
     * @param list Task list to be modified
     * @param ui Ui for display
     * @param s Storage for saving
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage s) {
        list.unmark(index);
        ui.unmark(list.get(index));
        s.saveList(list);
    }

    /**
     * Distinguishes if it is a bye command
     * @return Does not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
