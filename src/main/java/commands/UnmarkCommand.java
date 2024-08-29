package commands;

import exception.PrimoException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws PrimoException {
        ArrayList<Task> list = tasks.getTasks();
        if (index >= list.size() || index + 1 <= 0) {
            throw new PrimoException("Please select within the indexes of the tasklist!");
        }
        list.get(index).markAsUndone();
        System.out.println("\nEl Primo:");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(index));
        storage.updateStorage(tasks);
    }
}
