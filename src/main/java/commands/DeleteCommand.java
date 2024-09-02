package commands;

import parser.Parser;
import models.Task;

import java.util.List;

public class DeleteCommand implements Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Context context) {
        List<Task> tasks = context.tasks();
        Task task = tasks.get(this.taskIndex);
        tasks.remove(this.taskIndex);

        if (this.taskIndex < 0 || this.taskIndex >= tasks.size()) {
            throw new InvalidIndexException(tasks.size(), this.taskIndex);
        }

        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }
}
