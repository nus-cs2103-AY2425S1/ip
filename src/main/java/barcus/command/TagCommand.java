package barcus.command;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;

/**
 * Command to tag items at position pos
 */
public class TagCommand extends Command {
    private int pos;
    private String tag;

    /**
     * Constructor
     * @param pos index of item to tag
     */
    public TagCommand(int pos, String tag) {
        this.pos = pos;
        this.tag = tag;
        this.output = "";
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws BarcusException {
        if (!(pos > 0 && pos <= tasks.getLength())) {
            throw new BarcusException("please choose a number between 1 and " + tasks.getLength());
        }

        tasks.tagTask(pos - 1, tag);
        output += "Cool beans, have tagged task:\n";
        output += tasks.getTaskString(pos - 1) + "\n";

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
