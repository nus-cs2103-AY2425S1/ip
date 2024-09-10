package joe.command;

import java.util.ArrayList;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.Task;
import joe.task.TaskList;

/**
 * Represents the command to display all tasks when the user inputs 'list'.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        if (taskList.isEmpty()) {
            System.out.println(
                    "\tNo tasks yet! Looks like you've got a clean slate. "
                            + "Time to add some tasks and conquer the day!"
            );
            return;
        }
        ArrayList<Task> tasks = taskList.toArrayList();
        ui.printResponse("", tasks);
    }

    public static String getHelp() {
        return "To list all tasks, try: list";
    }
}
