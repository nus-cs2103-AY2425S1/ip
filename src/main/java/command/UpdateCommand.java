package command;

import parser.Parser;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.time.format.DateTimeParseException;

/**
 * Represents the update command which updates a specific
 * field with a new value.
 */
public class UpdateCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(String[] parts, TaskList list, Ui ui, Storage storage, Parser parser) {
        if (parts.length < 2) {
            return "Please input a task to update!!\n";
        } else {
            try {
                int index = Integer.parseInt(parts[1].substring(0, 1)) - 1;
                Task taskToUpdate = list.getTask(index);
                taskToUpdate =  parser.parseUpdateTask(parts[1], taskToUpdate);
                list.update(index, taskToUpdate);
                return ui.updateSuccess(index + 1);
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        }
    }
}
