package commands;

import parser.Parser;
import models.Event;
import models.Task;

import java.time.LocalDate;
import java.util.List;

public class CreateEventCommand implements Command {
    private final String name;
    private final LocalDate from;
    private final LocalDate to;

    public CreateEventCommand(String name, LocalDate from, LocalDate to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(Context context) {
        List<Task> tasks = context.tasks();
        Task task = new Event(this.name, this.from, this.to);
        tasks.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }
}
