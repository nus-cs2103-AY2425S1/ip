package mylo.command;

import mylo.data.InsufficientInfoException;
import mylo.storage.StorageOperationException;
import mylo.task.TaskList;
import mylo.task.TaskType;
import mylo.ui.Ui;
import mylo.utils.exceptions.IllegalValueException;

public class AddCommand extends Command{
    private TaskType type;
    private String details;

    public AddCommand(TaskType type, String details) {
        this.type = type;
        this.details = details;
    }

    @Override
    public void execute(TaskList list, Ui ui) throws StorageOperationException, InsufficientInfoException, IllegalValueException {
        String message = list.addTask(details, type);
        ui.show(message);
    }
}
