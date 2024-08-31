package ai.command;

import ai.TaskList;
import ai.Ui;

/**
 * Executes the command that lists out all tasks in the TaskList.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
