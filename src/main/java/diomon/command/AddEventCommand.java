package diomon.command;

import diomon.Storage;
import diomon.task.Task;
import diomon.task.TaskList;
import diomon.exception.MissingInputException;
import diomon.parser.Parser;
import diomon.ui.Ui;

public class AddEventCommand extends AddCommand{

    public AddEventCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert input != null;
        try {
            Task newTask = Parser.parseEvent(input);
            tasks.add(newTask);
            setResponse(String.format("Task: ( %s ) has been added.", newTask));
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
