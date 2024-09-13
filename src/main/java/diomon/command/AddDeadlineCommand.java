package diomon.command;

import diomon.Storage;
import diomon.Task;
import diomon.TaskList;
import diomon.exception.MissingInputException;
import diomon.parser.Parser;
import diomon.ui.Ui;

import java.time.LocalDate;

public class AddDeadlineCommand extends AddCommand{

    public AddDeadlineCommand(String input) {
        super(input);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input == null) {
                throw new MissingInputException();
            }
            Task newTask = Parser.parseDeadline(input);
            tasks.add(newTask);
            setResponse(String.format("Task: [%s] has been added.\\n", newTask));
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
