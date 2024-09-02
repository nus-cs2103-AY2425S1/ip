package commands;

import models.Task;
import models.ToDo;

import java.util.List;

public class CreateToDoCommand implements Command {
    private final String name;

    public CreateToDoCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(Context context) {
        List<Task> tasks = context.tasks();
        Task task = new ToDo(name);
        tasks.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }
}
