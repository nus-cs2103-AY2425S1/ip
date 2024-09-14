package tayoo.command;

import tayoo.Storage;
import tayoo.Tasklist;
import tayoo.Ui;
import tayoo.exception.TayooException;

public class DeleteTaskCommand extends Command {

    private final int taskNumber;

    public DeleteTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {

        String taskStr = tasklist.getTaskStr(this.taskNumber);

        if (tasklist.deleteTask(this.taskNumber)) {
            storage.deleteTxt(this.taskNumber);
            String toPrint = "Noted. I've removed this task:\n" + taskStr;

            int tasklistSize = tasklist.getSize();
            if (tasklistSize > 1) {
                toPrint += "\n Now you have " + tasklistSize + " tasks in your list";
            } else {
                toPrint += "\n Now you have " + tasklistSize + " task in your list";
            }
            ui.printText(toPrint);
        } else {
            throw new TayooException("I couldn't remove the task!");
        }
    }

    @Override
    public String guiExecute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        String taskStr = tasklist.getTaskStr(this.taskNumber);

        if (tasklist.deleteTask(this.taskNumber)) {
            storage.deleteTxt(this.taskNumber);
            String toPrint = "Noted. I've removed this task:\n" + taskStr;

            int tasklistSize = tasklist.getSize();
            if (tasklistSize > 1) {
                toPrint += "\n Now you have " + tasklistSize + " tasks in your list";
            } else {
                toPrint += "\n Now you have " + tasklistSize + " task in your list";
            }
            return toPrint;
        } else {
            throw new TayooException("I couldn't remove the task!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
