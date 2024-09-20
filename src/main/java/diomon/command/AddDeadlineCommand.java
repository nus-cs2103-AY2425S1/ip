package diomon.command;

import diomon.Storage;
import diomon.exception.MissingInputException;
import diomon.parser.Parser;
import diomon.task.Task;
import diomon.task.TaskList;

public class AddDeadlineCommand extends AddCommand{

    public AddDeadlineCommand(String input) {
        super(input);
    }
    @Override
    public void execute(TaskList tasks, Storage storage) {
        assert input != null;
        try {
            Task newTask = Parser.parseDeadline(input);
            tasks.add(newTask);
            setResponse(String.format("Task: ( %s ) has been added.", newTask));
        } catch (RuntimeException e) {
            throw new RuntimeException("Something went wrong, please check your input");
        }
    }
}
