package commands;

import commands.parser.Parser;
import models.Deadline;
import models.Task;

import java.time.LocalDate;
import java.util.List;

public class CreateDeadlineCommand implements Command {
    private final List<Task> tasks;
    private final String name;
    private final LocalDate by;

    public CreateDeadlineCommand(List<Task> tasks, String message) {
        this.tasks = tasks;

        // Remove the keyword from the message
        String messageArgs = Parser.parseMessage(message).args();
        String[] args = Parser.extractArgs(messageArgs, new String[] { "/by" });

        this.name = args[0];
        this.by = LocalDate.parse(args[1]);
    }

    @Override
    public void execute() {
        Task task = new Deadline(this.name, this.by);
        tasks.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", this.tasks.size());
    }
}
