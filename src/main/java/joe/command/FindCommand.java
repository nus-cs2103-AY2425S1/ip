package joe.command;

import java.util.ArrayList;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.Task;
import joe.task.TaskList;

/**
 * This class represents the 'find' command.
 */
public class FindCommand extends Command {

    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        ArrayList<Task> matchingArr = taskList.find(this.query);
        String msg = "Here are the matching tasks in your list:";
        ui.printResponse(msg, matchingArr);
    }

    public static String getHelp() {
        return "To find a task, try: find {query}";
    }
}
