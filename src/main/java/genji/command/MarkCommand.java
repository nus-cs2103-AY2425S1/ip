package genji.command;

import genji.task.TaskList;
import genji.Ui;
import genji.Storage;

/**
 * A class that deals with marking command
 */
public class MarkCommand extends Command{
    int index;

    /**
     * Constructor of marking command
     * @param index Number concerning which task to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Calling the mark method in task list class
     * @param list Task list to be modified
     * @param ui Ui for display
     * @param s Storage for saving
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage s) {
        list.mark(index);
        ui.mark(list.get(index));
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
