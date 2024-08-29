package commands;

import commands.parser.Parser;
import models.Task;

import java.util.List;

public class DeleteCommand implements Command {
    private final int itemIndex;
    private final List<Task> items;

    public DeleteCommand(List<Task> items, String message) {
        String messageArgs = Parser.parseMessage(message).args();
        int index = Integer.parseInt(messageArgs) - 1;

        if (index < 0 || index >= items.size()) {
            throw new InvalidIndexException(items.size(), index);
        }

        this.items = items;
        this.itemIndex = index;
    }

    @Override
    public void execute() {
        Task task = items.get(itemIndex);
        items.remove(itemIndex);

        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", this.items.size());
    }
}
