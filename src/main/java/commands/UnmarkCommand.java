package commands;

import commands.parser.Parser;
import models.Task;
import models.TaskList;

public class UnmarkCommand implements Command {
    private final int itemIndex;
    private final TaskList items;

    public UnmarkCommand(TaskList items, String message) {
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
        task.unmarkDone();
        System.out.printf("Marked this task as not yet done:\n%s\n", task);
    }
}
