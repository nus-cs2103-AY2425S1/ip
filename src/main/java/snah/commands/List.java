package snah.commands;

import snah.TaskList;
import snah.util.Storage;

/**
 * List command to list all tasks in the task list
 */
public class List extends Command {
    public List(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        String tasksList = tasks.list();
        String response = "";
        if (tasksList.isEmpty()) {
            response += "There are no tasks in your list.\nCreate a new one with the 'todo', "
                    + "'deadline' or 'event' command.";
        } else {
            response = "Here are the tasks in your list:\n";
            response += tasksList;
        }
        return response;
    }

}
