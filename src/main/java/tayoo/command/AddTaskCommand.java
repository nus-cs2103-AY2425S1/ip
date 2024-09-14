package tayoo.command;

import tayoo.Storage;
import tayoo.Tasklist;
import tayoo.Tayoo;
import tayoo.Ui;
import tayoo.exception.TayooException;
import tayoo.tasks.Task;

/**
 * The AddTaskCommand class contains all commands to be executed when adding a task to the tasklist
 */
public class AddTaskCommand extends Command {

    private final Task taskToAdd;

    public AddTaskCommand(Task task) {
        this.taskToAdd = task;
    }

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {

        if (tasklist.addTask(this.taskToAdd)) {
            storage.addToTxt(this.taskToAdd);

            String toPrint = "Got it. I've added this task: \n" + this.taskToAdd.toString();
            int tasklistSize = tasklist.getSize();
            if (tasklistSize > 1) {
                toPrint += "\n Now you have " + tasklistSize + " tasks in your list";
            } else {
                toPrint += "\n Now you have " + tasklistSize + " task in your list";
            }
            ui.printText(toPrint);
        } else {
            throw new TayooException("You have too many tasks in your tasklist!");
        }
    }

    @Override
    public String guiExecute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        if (tasklist.addTask(this.taskToAdd)) {
            storage.addToTxt(this.taskToAdd);

            String toPrint = "Got it. I've added this task: \n" + this.taskToAdd.toString();
            int tasklistSize = tasklist.getSize();
            if (tasklistSize > 1) {
                toPrint += "\n Now you have " + tasklistSize + " tasks in your list";
            } else {
                toPrint += "\n Now you have " + tasklistSize + " task in your list";
            }
            return toPrint;
        } else {
            throw new TayooException("You have too many tasks in your tasklist!");
        }
    }

    @Override
    public String toString() {
        return this.taskToAdd.toString();

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
