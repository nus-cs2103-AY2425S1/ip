package commands;

import commands.parser.Parser;
import models.Task;

import java.util.List;

public class MarkCommand implements Command {
    private final int taskIndex;
    private final List<Task> tasks;

    public MarkCommand(List<Task> tasks, String message) {
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
        task.markDone();
        System.out.printf("Marked this task as done:\n%s\n", task);
    }
}
