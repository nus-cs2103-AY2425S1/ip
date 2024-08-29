package commands;

import commands.parser.Parser;
import models.Event;
import models.Task;

import java.util.List;

public class CreateEventCommand implements Command {
    private final List<Task> items;
    private final String name;
    private final String from;
    private final String to;

    public CreateEventCommand(List<Task> items, String message) {
        this.items = items;

        // Remove the keyword from the message
        String messageArgs = Parser.parseMessage(message).args();
        String[] args = Parser.extractArgs(messageArgs, new String[] { "/from", "/to" });

        this.name = args[0];
        this.from = args[1];
        this.to = args[2];
    }

    @Override
    public void execute() {
        Task task = new Event(this.name, this.from, this.to);
        items.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", this.items.size());
    }
}
