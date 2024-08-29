package commands;

import commands.parser.Parser;
import models.Task;

import java.util.List;

public class DeleteCommand implements Command {
    private final int taskIndex;
    private final List<Task> tasks;

    public DeleteCommand(List<Task> tasks, String message) {
        String messageArgs = Parser.parseMessage(message).args();
        int index = Parser.parseInt(messageArgs) - 1;

        if (index < 0 || index >= tasks.size()) {
            throw new InvalidIndexException(tasks.size(), index);
        }

        this.tasks = tasks;
        this.taskIndex = index;
    }

    @Override
    public void execute() {
        Task task = tasks.get(taskIndex);
        tasks.remove(taskIndex);

        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", this.tasks.size());
    }
}
