package ai.command;

import ai.TaskList;
import ai.Ui;

public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
