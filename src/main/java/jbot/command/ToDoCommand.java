package jbot.command;

import jbot.task.EmptyToDoDescriptionException;
import jbot.task.Task;
import jbot.task.ToDoTask;
import jbot.util.TaskList;

public class ToDoCommand extends AddCommand {
    private static final ToDoCommand instance = new ToDoCommand();
    private ToDoCommand() {
    };
    public static ToDoCommand getInstance() {
        return ToDoCommand.instance;
    }
    @Override
    public void run(String input) {
        try {
            Task task = new ToDoTask(input);
            TaskList.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.printf("  %1$s\n", task);
            super.run(input);
        } catch (EmptyToDoDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }
}
