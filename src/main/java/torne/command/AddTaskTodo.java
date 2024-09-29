package torne.command;

import torne.command.argument.CommandArgument;
import torne.command.argument.CommandArgumentList;
import torne.exception.TorneException;
import torne.task.Task;
import torne.task.TaskTodo;

import java.util.List;
import java.util.Map;

/**
 * Creates a new task with no date/time, with type {@link TaskTodo}.
 */
public class AddTaskTodo extends Command {
    protected AddTaskTodo() {
        super(
                "todo",
                "Adds a Todo task",
                new CommandArgumentList(List.of(
                        new CommandArgument("", "Name of task", "Name of task to be added")
                ))
        );
    }

    @Override
    public void execute(Map<String, String> arguments) throws TorneException {
        Task task = TaskTodo.fromCommand(arguments.get(""));

        taskHandler.addTask(task);
        int count = taskHandler.getTaskCount();

        String message = "Alright, I'll add this task:\n" + task
                + String.format("\nNow you have %d task", count)
                + ((count > 1) ? "s!" : "!");
        output.writeText(message);
    }
}
