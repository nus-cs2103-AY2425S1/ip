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
        String response = "Here are the tasks in your list:\n";
        response += tasks.list();
        return response;
    }

}
