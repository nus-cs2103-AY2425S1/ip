package joe.command;

import joe.Commands;
import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.TaskList;
public class ToggleCommand extends Command {
    String[] inputArr;
    Commands c;
    public ToggleCommand(Commands c, String[] inputArr) {
        this.inputArr = inputArr;
        this.c = c;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        int idx = Integer.parseInt(this.inputArr[1]); // gets the task index to mark or unmark
        if (idx > taskList.getSize() || idx < 1) { // check that task index is valid
            throw new JoeException("Please input a valid task index");
        }
        switch (c) {
        case MARK -> taskList.markTask(idx);
        case UNMARK -> taskList.unmarkTask(idx);
        }
    }
}
