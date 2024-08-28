package pikappi.command;

import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;
import pikappi.exception.PikappiException;
import pikappi.task.TodoTask;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PikappiException {
        tasks.addTask(new TodoTask(description));
        storage.save(tasks);
    }
}
