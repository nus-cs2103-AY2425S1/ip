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
    public void execute(TaskList tasks, Ui ui) {
        for (int i = 0; i < tasks.size(); i++) {
            Task temp = tasks.get(i);
            if (temp.contains(argument)) {
                System.out.println(temp);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

