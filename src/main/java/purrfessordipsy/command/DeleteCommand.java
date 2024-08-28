package purrfessordipsy.command;

import purrfessordipsy.exception.InvalidCommandException;
import purrfessordipsy.task.Task;
import purrfessordipsy.tasklist.TaskList;
import purrfessordipsy.ui.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteCommand extends Command {
    private static final Pattern DELETE_PATTERN = Pattern.compile("^delete (\\d+)");

    public DeleteCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    @Override
    public void execute() throws InvalidCommandException  {
        Matcher matcher = DELETE_PATTERN.matcher(userInput);

        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group(1));
            Task deletedTask = tasks.deleteTask(index);
            ui.printTaskDeletedMessage(deletedTask, tasks.getSize());
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_DELETE_COMMAND);
        }
    }
}
