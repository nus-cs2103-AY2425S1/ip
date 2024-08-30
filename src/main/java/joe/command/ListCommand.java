package joe.command;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.Task;
import joe.task.TaskList;

import java.util.ArrayList;

/**
 * Represents the command to display all tasks when the user inputs 'list'.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        if (taskList.isEmpty()) {
             System.out.println("\t\uD83C\uDF89 No tasks yet! Looks like you've got a clean slate. Time to add some tasks and conquer the day! \uD83D\uDE80");
             return;
        }
        ArrayList<Task> tasks = taskList.toArrayList();
        ui.printResponse("", tasks);
    }
}
