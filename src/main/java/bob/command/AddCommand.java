package bob.command;

import java.io.IOException;

import bob.Storage;
import bob.TaskList;
import bob.task.Task;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            tasks.add(task);
            storage.save(tasks);
            return (
                    String.format("Here's the added task:\n" +
                            "\t%s\n" +
                            "Now you have %s tasks in the list.", task, tasks.size())
            );
        } catch (IOException e) {
            return "I can't remember that for some reason T T";
        }
    }
}

