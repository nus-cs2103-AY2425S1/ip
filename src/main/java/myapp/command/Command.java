package myapp.command;

import myapp.core.*;
import myapp.task.TaskList;

public abstract class Command {
    public Command() {}

    public abstract void execute(TaskList tasks, BingBongUI ui, Storage storage)
            throws BingBongException, IndexOutOfBoundsException;

    public abstract boolean isExit();

    public void saveTasks(TaskList tasks, BingBongUI ui, Storage storage) {
        try {
            storage.save(tasks);
        } catch (BingBongException e) {
            ui.showResponse("Unable to save tasks in hard disk");
        }
    }
}
