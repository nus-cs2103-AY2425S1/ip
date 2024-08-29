package commands;

import commands.parser.MissingArgumentException;
import commands.parser.Parser;
import models.Task;
import models.ToDo;

import java.util.List;

public class CreateToDoCommand implements Command {
    private final List<Task> items;
    private final String name;

    public CreateToDoCommand(List<Task> items, String message) {
        this.items = items;

        // Remove the keyword from the message
        this.name = Parser.parseMessage(message).args();
        if (this.name.isEmpty()) {
            throw new MissingArgumentException(1, 0);
        }
    }

    @Override
    public void execute() {
        Task task = new ToDo(name);
        items.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", this.items.size());
    }
}
