package barcus.command;

import java.util.ArrayList;
import java.util.List;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;

/**
 * Command to mark item at pos
 */
public class MarkCommand extends Command {
    private int[] pos;

    /**
     * Constructor
     * @param pos index of item to mark
     */
    public MarkCommand(int[] pos) {
        this.pos = pos;
        this.output = "";
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws BarcusException {
        List<Integer> queue = new ArrayList<>();
        for (int p: pos) {
            if (p > 0 && p <= tasks.getLength()) {
                // tasks.get(pos - 1).markDone();
                //tasks.markTask(p - 1);
                queue.add(p - 1);

            } else {
                //ui.showError("please choose a number between 1 and " + tasks.getLength());
                throw new BarcusException("please choose a number between 1 and " + tasks.getLength());
            }
        }
        assert !queue.isEmpty();

        output += "Good job! Have marked as done:\n";
        for (int i: queue) {
            tasks.markTask(i);
            output += tasks.getTaskString(i) + "\n";
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }

}
