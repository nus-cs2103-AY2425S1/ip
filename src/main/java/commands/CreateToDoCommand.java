package commands;

import parser.Parser;
import models.Task;
import models.ToDo;

import java.util.List;

public class CreateToDoCommand implements Command {
    private final List<Task> tasks;
    private final String name;

    public CreateToDoCommand(List<Task> tasks, String message) {
        this.tasks = tasks;

        String messageArgs = Parser.parseMessage(message).args();
        this.name = Parser.extractArgs(messageArgs, new String[]{})[0];
    }

    @Override
    public void execute() {
        Task task = new ToDo(name);
        tasks.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", this.tasks.size());
    }
}
