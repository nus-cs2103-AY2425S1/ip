package barcus.command;

import java.util.ArrayList;
import java.util.List;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.task.Task;
import barcus.tasklist.TaskList;

/**
 * Command to delete item at pos
 */
public class DeleteCommand extends Command {
    private int[] pos;

    /**
     * Constructor
     * @param pos index of item to delete
     */
    public DeleteCommand(int[] pos) {
        this.pos = pos;
        this.output = "";
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws BarcusException {
        List<Integer> queue = new ArrayList<>();
        for (int p: pos) {
            if (p > 0 && p <= tasks.getLength()) {
                //tasks.get(pos - 1).markDone();
                //talk("Good job! Have marked as done: " + tasks.get(pos - 1));
                //Task temp = tasks.deleteTask(pos - 1);
                //curr--;
                //ui.talk("Removed task: " + temp + "\nThere are "
                //        + tasks.getLength() + " task(s) in the list.");
                //output = "Removed task: " + temp + "\nThere are "
                //        + tasks.getLength() + " task(s) in the list.";
                queue.add(p - 1);
            } else {
                throw new BarcusException("please choose a number between 1 and " + tasks.getLength());
            }
        }

        assert !queue.isEmpty();

        output += "Removed task:\n";
        for (int i: queue) {
            Task temp = tasks.deleteTask(i);
            output += temp + "\n";
        }
        output += "There are " + tasks.getLength() + " task(s) in the list.";
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
