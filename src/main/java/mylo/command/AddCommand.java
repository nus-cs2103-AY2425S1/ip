package mylo.command;

import mylo.data.InsufficientInfoException;
import mylo.storage.StorageOperationException;
import mylo.task.TaskList;
import mylo.task.TaskType;
import mylo.ui.Ui;
import mylo.utils.exceptions.IllegalValueException;

public class AddCommand extends Command{
    private final TaskType TYPE;
    private final String DETAILS;

    public AddCommand(TaskType type, String details) {
        this.TYPE = type;
        this.DETAILS = details;
    }

    @Override
    public void execute(TaskList list, Ui ui) throws StorageOperationException, InsufficientInfoException, IllegalValueException {
        String message = list.addTask(DETAILS, TYPE);
        ui.show(message);
    }
}
