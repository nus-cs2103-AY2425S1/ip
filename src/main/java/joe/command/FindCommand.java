package joe.command;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.Task;
import joe.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String query;
    public FindCommand(String query) {
       this.query = query;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        ArrayList<Task> matchingArr =  taskList.find(this.query);
        String msg = "Here are the matching tasks in your list:";
        ui.printResponse(msg, matchingArr);
    }
}
