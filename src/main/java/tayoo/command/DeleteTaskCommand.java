package tayoo.command;

import tayoo.Storage;
import tayoo.Tasklist;
import tayoo.Ui;
import tayoo.exception.TayooException;
import tayoo.tasks.Task;

import java.util.Collections;
import java.util.List;

public class DeleteTaskCommand extends Command {

    private final List<Integer> taskNumbers;

    public DeleteTaskCommand(List<Integer> taskNumber) {
        this.taskNumbers = taskNumber;
        Collections.sort(taskNumbers, Collections.reverseOrder());

        taskNumbers.replaceAll(x -> x - 1);
    }

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {

        StringBuilder toPrint = new StringBuilder();

        for (int taskToDelete: taskNumbers) {

            if (taskToDelete < 0) {
                throw new TayooException("Expected task number > 0");
            } else if (taskToDelete > Tasklist.MAXIMUM_CAPACITY) {
                throw new TayooException("Expected task number < " + Tasklist.MAXIMUM_CAPACITY);
            } else if (taskToDelete >= tasklist.getSize()) {
                toPrint.append("Could not find task number ").append(taskToDelete + 1).append("\n");
                continue;
            }

            Task deletedTask = tasklist.deleteTask(taskToDelete);

            if (deletedTask != null) {
                storage.deleteTxt(taskToDelete);
                toPrint.append("Deleted: ").append(deletedTask.toString()).append("\n");
            } else {
                throw new TayooException("Exception occurred when deleting task");
            }
        }

        int tasklistSize = tasklist.getSize();
        if (tasklistSize > 1) {
            toPrint.append("\n Now you have ").append(tasklistSize).append(" tasks in your list");
        } else {
            toPrint.append("\n Now you have ").append(tasklistSize).append(" task in your list");
        }

        ui.printText(toPrint.toString());
    }

    @Override
    public String guiExecute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        StringBuilder toReturn = new StringBuilder();

        for (int taskToDelete: taskNumbers) {

            if (taskToDelete < 0) {
                throw new TayooException("Expected task number > 0");
            } else if (taskToDelete > Tasklist.MAXIMUM_CAPACITY) {
                throw new TayooException("Expected task number < " + Tasklist.MAXIMUM_CAPACITY);
            } else if (taskToDelete >= tasklist.getSize()) {
                toReturn.append("Could not find task number ").append(taskToDelete + 1).append("\n");
                continue;
            }

            Task deletedTask = tasklist.deleteTask(taskToDelete);

            if (deletedTask != null) {
                storage.deleteTxt(taskToDelete);
                toReturn.append("Deleted: ").append(deletedTask.toString()).append("\n");
            } else {
                throw new TayooException("Exception occurred when deleting task");
            }
        }

        int tasklistSize = tasklist.getSize();
        if (tasklistSize > 1) {
            toReturn.append("\n Now you have ").append(tasklistSize).append(" tasks in your list");
        } else {
            toReturn.append("\n Now you have ").append(tasklistSize).append(" task in your list");
        }

        return toReturn.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
