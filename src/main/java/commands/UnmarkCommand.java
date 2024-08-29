package commands;

import commands.parser.Parser;
import models.Task;

import java.util.List;

public class UnmarkCommand implements Command {
    private final int taskIndex;
    private final List<Task> tasks;

    public UnmarkCommand(List<Task> tasks, String message) {
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
        task.unmarkDone();
        System.out.printf("Marked this task as not yet done:\n%s\n", task);
    }
}
