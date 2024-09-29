package torne.command;

import torne.command.argument.CommandArgument;
import torne.command.argument.CommandArgumentList;
import torne.exception.TorneException;
import torne.task.Task;
import torne.task.TaskDeadline;

import java.util.List;
import java.util.Map;

/**
 * Creates a new task with a date/time as the deadline, with type {@link TaskDeadline}.
 */
public class AddTaskDeadline extends Command {
    protected AddTaskDeadline() {
        super(
                "deadline",
                "Adds a Deadline task",
                new CommandArgumentList(List.of(
                        new CommandArgument("", "Name of task", "Name of task to be added"),
                        new CommandArgument("by", "yyyy-MM-dd HHmm", "Deadline to complete the task by")
                ))
        );
    }

    @Override
    public void execute(Map<String, String> arguments) throws TorneException {
        Task task = TaskDeadline.fromCommand(arguments.get(""), arguments.get("by"));

        taskHandler.addTask(task);
        int count = taskHandler.getTaskCount();

        String message = "Alright, I'll add this task:\n" + task
                + String.format("\nNow you have %d task", count)
                + ((count > 1) ? "s!" : "!");
        output.writeText(message);
    }
}
