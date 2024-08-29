package commands;

import commands.parser.Parser;
import models.Task;

import java.util.List;

public class UnmarkCommand implements Command {
    private final int itemIndex;
    private final List<Task> items;

    public UnmarkCommand(List<Task> items, String message) {
        String messageArgs = Parser.parseMessage(message).args();
        int index = Parser.parseInt(messageArgs) - 1;

        this.items = items;
        this.itemIndex = index;
    }

    @Override
    public void execute() {
        Task task = items.get(itemIndex);
        task.unmarkDone();
        System.out.printf("Marked this task as not yet done:\n%s\n", task);
    }
}
