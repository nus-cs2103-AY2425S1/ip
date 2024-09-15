package tayoo.command;

import tayoo.Storage;
import tayoo.Tasklist;
import tayoo.Ui;
import tayoo.exception.TayooException;

import java.util.Collections;
import java.util.List;

public class MarkTaskCommand extends Command {

    private final boolean isComplete;
    private final List<Integer> tasksToMark;


    public MarkTaskCommand(List<Integer> markIndicesList, boolean isComplete) {
        this.isComplete = isComplete;
        this.tasksToMark = markIndicesList;
        Collections.sort(tasksToMark);

        tasksToMark.replaceAll(x -> x - 1);
    }

    private String markTaskHelper(int taskToMark, Tasklist tasklist, Storage storage) throws TayooException {
        if (tasklist.markTask(taskToMark)) {
            storage.updateTxt(taskToMark, isComplete);
            return "Marked: " + tasklist.getTaskStr(taskToMark) + "\n";
        } else {
            return "Already marked: " + tasklist.getTaskStr(taskToMark) + "\n";
        }
    }
    private String unmarkTaskHelper(int taskToMark, Tasklist tasklist, Storage storage) throws TayooException {
        if (tasklist.unmarkTask(taskToMark)) {
            storage.updateTxt(taskToMark, isComplete);
            return "Unmarked: " + tasklist.getTaskStr(taskToMark) + "\n";
        } else {
            return "Already unmarked: " + tasklist.getTaskStr(taskToMark) + "\n";
        }
    }


    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {

        StringBuilder toPrint = new StringBuilder();

        if (isComplete) {
            toPrint.append("Okay! I've marked these tasks:\n");
        } else {
            toPrint.append("Okay! I've unmarked these tasks:\n");
        }

        for (int taskToMark : tasksToMark) {
            if (taskToMark < 0) {
                throw new TayooException("Expected task number > 0");
            } else if (taskToMark > Tasklist.MAXIMUM_CAPACITY) {
                throw new TayooException("Expected task number < " + Tasklist.MAXIMUM_CAPACITY);
            } else if (taskToMark >= tasklist.getSize()) {
                toPrint.append("Could not find task number ").append(taskToMark + 1).append("\n");
                continue;
            }

            if (isComplete) {
                toPrint.append(markTaskHelper(taskToMark, tasklist, storage));
            } else {
                toPrint.append(unmarkTaskHelper(taskToMark, tasklist, storage));
            }
        }

        ui.printText(toPrint.toString());
    }

    public String guiExecute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {

        StringBuilder toReturn = new StringBuilder();

        if (isComplete) {
            toReturn.append("Okay! I've marked these tasks:\n");
        } else {
            toReturn.append("Okay! I've unmarked these tasks:\n");
        }


        for (int taskToMark : tasksToMark) {
            if (taskToMark < 0) {
                throw new TayooException("Expected task number > 0");
            } else if (taskToMark > Tasklist.MAXIMUM_CAPACITY) {
                throw new TayooException("Expected task number < " + Tasklist.MAXIMUM_CAPACITY);
            } else if (taskToMark >= tasklist.getSize()) {
                toReturn.append("Could not find task number ").append(taskToMark + 1).append("\n");
                continue;
            }

            if (isComplete) {
                toReturn.append(markTaskHelper(taskToMark, tasklist, storage));
            } else {
                toReturn.append(unmarkTaskHelper(taskToMark, tasklist, storage));
            }
        }

        return toReturn.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
