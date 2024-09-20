package ai.command;

import ai.TaskList;
import ai.Ui;
import ai.task.Task;

/**
 * Executes the find command that prints out the task that contains the user specified word in the description.
 */
public class FindCommand extends Command {
    private String argument;

    public FindCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task temp = tasks.get(i);
            if (temp.contains(argument)) {
                list += temp + "\n";
            }
        }

        if (list == "") {
            return "Sorry >.< no tasks found :p\n";
        }

        return "I found your tasks!!!\n\n"
                + list + "\n"
                + "Tell me if you need anyth else, ehe :3\n";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

