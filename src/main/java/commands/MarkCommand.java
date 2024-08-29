package commands;

import commands.parser.Parser;
import models.Task;
import models.TaskList;

public class MarkCommand implements Command {
    private final int itemIndex;
    private final TaskList items;

    public MarkCommand(TaskList items, String message) {
        String messageArgs = Parser.parseMessage(message).args();
        int index = Parser.parseInt(messageArgs) - 1;

        if (index < 0 || index >= items.size()) {
            throw new InvalidIndexException(items.size(), index);
        }

        this.items = items;
        this.itemIndex = index;
    }

    @Override
    public void execute() {
        Task task = items.get(itemIndex);
        task.markDone();
        System.out.printf("Marked this task as done:\n%s\n", task);
    }
}
