package barcus.command;

import java.util.ArrayList;
import java.util.List;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;

/**
 * Command to unmark item at the position
 */
public class UnmarkCommand extends Command {
    private int[] pos;

    /**
     * Constructor
     * @param pos index of item to unmark
     */
    public UnmarkCommand(int[] pos) {
        this.pos = pos;
        this.output = "";
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws BarcusException {
        List<Integer> queue = new ArrayList<>();
        for (int p: pos) {
            if (p > 0 && p <= tasks.getLength()) {
                //tasks.get(pos - 1).unmarkDone();
                //tasks.unmarkTask(p - 1);
                //ui.talk("No prob, have marked as undone: " + tasks.getTaskString(pos - 1));
                //output = "No prob, have marked as undone: " + tasks.getTaskString(pos - 1);
                queue.add(p - 1);
            } else {
                throw new BarcusException("please choose a number between 1 and " + tasks.getLength());
            }
        }

        assert !queue.isEmpty();

        output += "No prob, have marked as undone:\n";
        for (int i: queue) {
            tasks.unmarkTask(i);
            output += tasks.getTaskString(i) + "\n";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
