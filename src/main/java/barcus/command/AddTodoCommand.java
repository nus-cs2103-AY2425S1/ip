package barcus.command;

import barcus.tasklist.TaskList;
import barcus.ui.Ui;
import barcus.storage.Storage;
import barcus.task.*;

public class AddTodoCommand extends AddCommand {
    public AddTodoCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Todo(this.description);
        tasks.addTask(t);
        ui.talk("Added task: " + t + "\nThere are " + tasks.getLength() + " task(s) in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
