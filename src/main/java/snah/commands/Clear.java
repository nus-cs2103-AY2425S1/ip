package snah.commands;

import snah.TaskList;
import snah.util.Storage;

/**
 * Clear command to clear all tasks in the task list for user to easily delete
 * all previous tasks
 */
public class Clear extends Command {
    public Clear(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.clear();
        tasks.save(storage);
        return "Tasks cleared";
    }

}
