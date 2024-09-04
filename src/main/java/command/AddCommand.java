package command;

import data.InsufficientInfoException;
import storage.StorageOperationException;
import task.TaskList;
import task.TaskType;
import ui.Ui;
import utils.exceptions.IllegalValueException;

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
