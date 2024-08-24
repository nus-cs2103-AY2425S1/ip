package myapp.command;


import myapp.core.BingBongException;
import myapp.core.BingBongUi;
import myapp.core.Storage;
import myapp.task.TaskList;

public abstract class Command {
    public Command() {}

    public abstract void execute(TaskList tasks, BingBongUi ui, Storage storage)
            throws BingBongException, IndexOutOfBoundsException;

    public abstract boolean isExit();

    public void saveTasks(TaskList tasks, BingBongUi ui, Storage storage) {
        try {
            storage.save(tasks);
        } catch (BingBongException e) {
            ui.showResponse("Unable to save tasks in hard disk");
        }
    }
}
