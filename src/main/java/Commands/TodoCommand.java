package commands;

import java.util.ArrayList;
import applemazer.Storage;
import tasks.Task;
import tasks.Todo;

public class TodoCommand extends Command {
    private final String desc;

    public TodoCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Storage storage) {
        Task task = new Todo(desc);
        tasks.add(task);
        task.printTaskAddedMessage();
        storage.save();
    }

    @Override
    public boolean continueProcessing() {
        return true;
    }
}
