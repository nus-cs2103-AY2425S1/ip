package commands;

import commands.parser.Parser;
import models.Event;
import models.Task;

import java.time.LocalDate;
import java.util.List;

public class CreateEventCommand implements Command {
    private final List<Task> tasks;
    private final String name;
    private final LocalDate from;
    private final LocalDate to;

    public CreateEventCommand(List<Task> tasks, String message) {
        this.tasks = tasks;

        // Remove the keyword from the message
        String messageArgs = Parser.parseMessage(message).args();
        String[] args = Parser.extractArgs(messageArgs, new String[] { "/from", "/to" });

        this.name = args[0];
        this.from = LocalDate.parse(args[1]);
        this.to = LocalDate.parse(args[2]);
    }

    @Override
    public void execute() {
        Task task = new Event(this.name, this.from, this.to);
        tasks.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", this.tasks.size());
    }
}
