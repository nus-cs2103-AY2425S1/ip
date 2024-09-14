package barcus.command;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;
import barcus.ui.Ui;

public class UntagCommand extends Command {
    private int pos;
    private String tag;

    /**
     * Constructor
     * @param pos index of item to untag
     */
    public UntagCommand(int pos, String tag) {
        this.pos = pos;
        this.tag = tag;
        this.output = "";
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BarcusException {
        if (!(pos > 0 && pos <= tasks.getLength())) {
            throw new BarcusException("please choose a number between 1 and " + tasks.getLength());
        }

        tasks.untagTask(pos - 1, tag);
        output += "Sureee, have untagged task:\n";
        output += tasks.getTaskString(pos -1) + "\n";

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
