package torne.command;

import torne.command.argument.CommandArgument;
import torne.command.argument.CommandArgumentList;
import torne.exception.TorneException;
import torne.task.Task;
import torne.task.TaskEvent;

import java.util.List;
import java.util.Map;

/**
 * Creates a new task that starts at a date/time and ends at a date/time,
 * with type {@link TaskEvent}.
 */
public class AddTaskEvent extends Command {
    protected AddTaskEvent() {
        super(
                "event",
                "Adds an Event task",
                new CommandArgumentList(List.of(
                        new CommandArgument("", "Name of task", "Name of task"),
                        new CommandArgument("from", "yyyy-MM-dd HHmm", "Start"),
                        new CommandArgument("to", "yyyy-MM-dd HHmm", "End")
                ))
        );
    }

    @Override
    public void execute(Map<String, String> arguments) throws TorneException {
        Task task = TaskEvent.fromCommand(arguments.get(""), arguments.get("from"), arguments.get("to"));

        taskHandler.addTask(task);
        int count = taskHandler.getTaskCount();

        String message = "Alright, I'll add this task:\n" + task
                + String.format("\nNow you have %d task", count)
                + ((count > 1) ? "s!" : "!");
        output.writeText(message);
    }
}
