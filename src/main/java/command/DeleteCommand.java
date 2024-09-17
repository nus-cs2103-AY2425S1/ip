package command;

import parser.Parser;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents the delete command which deletes a task from
 * the task list.
 */
public class DeleteCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(String[] parts, TaskList list, Ui ui, Storage storage, Parser parser) {
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            assert index >= 0 : "Please input a valid task number!\n";
            return list.removeTask(index);
        } catch (NumberFormatException e) {
            return ui.NumberFormatExceptionMessage();
        }
    }
}
