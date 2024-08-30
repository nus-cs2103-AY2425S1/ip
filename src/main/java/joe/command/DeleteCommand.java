package joe.command;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.TaskList;
public class DeleteCommand extends Command {
    String[] inputArr;
    public DeleteCommand(String[] inputArr) {
        this.inputArr = inputArr;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        int idx = Integer.parseInt(inputArr[1]);
        if (idx > taskList.getSize() || idx < 1) { // check that task index is valid
            throw new JoeException("Please input a valid task index");
        }
        taskList.removeTask(idx);
    }
}
