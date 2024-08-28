package purrfessordipsy.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import purrfessordipsy.exception.InvalidCommandException;
import purrfessordipsy.task.Task;
import purrfessordipsy.tasklist.TaskList;
import purrfessordipsy.ui.Ui;

public class MarkCommand extends Command {
    private static final Pattern MARK_PATTERN = Pattern.compile("(mark|unmark) (\\d+)");

    public MarkCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    @Override
    public void execute() throws InvalidCommandException {
        Matcher markMatcher = MARK_PATTERN.matcher(super.userInput);
        if (markMatcher.matches()) {
            String action = markMatcher.group(1);
            int userGivenIndex = Integer.parseInt(markMatcher.group(2));
            int index = userGivenIndex - 1; // Since tasks are 0-indexed.
            if (index >= 0 && index < tasks.getSize()) {
                if (action.equals("mark")) {
                    markTaskAsDone(index);
                } else {
                    markTaskAsUndone(index);
                }
            } else {
                throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_MARK_INDEX);
            }
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_MARK_COMMAND);
        }
    }

    private void markTaskAsDone(int index) {
        Task task = tasks.getTask(index);
        task.markAsDone();
        ui.printMarkTaskDoneMessage(task);
        saveTasksToLocalDisk();
    }

    private void markTaskAsUndone(int index) {
        Task task = tasks.getTask(index);
        task.markAsUndone();
        ui.printMarkTaskUndoneMessage(task);
        saveTasksToLocalDisk();
    }
}
