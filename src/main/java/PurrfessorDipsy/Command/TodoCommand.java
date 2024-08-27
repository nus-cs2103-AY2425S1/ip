package PurrfessorDipsy.Command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import PurrfessorDipsy.Exception.InvalidCommandException;
import PurrfessorDipsy.Task.ToDo;
import PurrfessorDipsy.TaskList.TaskList;
import PurrfessorDipsy.Ui.Ui;

public class TodoCommand extends Command {
    private static final Pattern TODO_PATTERN = Pattern.compile("^todo (.+)$");

    public TodoCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    @Override
    public void execute() throws InvalidCommandException {
        Matcher matcher = TODO_PATTERN.matcher(userInput);
        if (matcher.matches()) {
            String description = matcher.group(1);
            ToDo todo = new ToDo(description);
            tasks.addTask(todo);
            ui.printTaskAddedMessage(todo, tasks.getSize());
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_TODO);
        }
    }
}
