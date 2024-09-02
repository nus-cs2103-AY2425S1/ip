package commands;

import parser.Parser;
import models.Deadline;
import models.Task;

import java.time.LocalDate;
import java.util.List;

public class CreateDeadlineCommand implements Command {
    private final String name;
    private final LocalDate by;

    public CreateDeadlineCommand(String name, LocalDate by) {
        this.name = name;
        this.by = by;
    }

    @Override
    public void execute(Context context) {
        List<Task> tasks = context.tasks();
        Task task = new Deadline(this.name, this.by);
        tasks.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }
}
