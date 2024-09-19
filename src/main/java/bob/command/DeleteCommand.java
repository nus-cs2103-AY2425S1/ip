package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.IncorrectArgumentException;
import bob.exception.MissingArgumentException;
import bob.task.Task;

import java.util.Map;

public class DeleteCommand extends Command {
    public static final String COMMAND = "delete";

    public DeleteCommand(Map<String, String> arguments) {
        super(arguments);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String argument = this.arguments.get("");
        if (argument == null || argument.isBlank()) {
            throw new MissingArgumentException("index of the task that you want to delete");
        }

        int i;
        try {
            i = Integer.parseInt(argument) - 1;
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException("an integer");
        }
        if (i < 0 || i >= tasks.size()) {
            throw new IncorrectArgumentException("a valid index");
        }

        Task task = tasks.remove(i);
        ui.printWithFormat("OK, I've removed this task:\n" +
                task + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.");
    }
}
