package lict.command;

import lict.Storage;
import lict.TaskList;
import lict.Ui;

/**
 * The {@code ListCommand} class is responsible for handling the command to list all tasks.
 * When executed, it triggers the UI to display the current list of tasks.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
