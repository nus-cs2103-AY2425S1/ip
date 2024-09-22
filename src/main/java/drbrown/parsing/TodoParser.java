package drbrown.parsing;

import drbrown.command.AddCommand;
import drbrown.command.Command;
import drbrown.task.Task;
import drbrown.task.Todo;
import drbrown.utils.DrBrownException;
import drbrown.utils.Ui;

/**
 * A parser that handles the "todo" command input.
 * Responsible for parsing the input to create a {@link Todo} task.
 */
public class TodoParser extends Parsing {

    public TodoParser(String[] inputSplit) {
        super(inputSplit);
    }

    /**
     * Parses the input to create an {@link AddCommand} that adds a {@link Todo} task.
     *
     * @return An instance of {@link AddCommand} containing the {@link Todo} task.
     * @throws DrBrownException If the input is invalid, such as missing the description.
     */
    public Command parse() throws DrBrownException {
        assert this.getInputSplit() != null : "Input string array should not be null";
        try {
            if (this.getInputSplit()[1].trim().isEmpty()) {
                throw new DrBrownException(Ui.getTodoExceptionNoDescription());
            }

            String[] todoSplit = this.getInputSplit()[1].split("/priority");

            if (todoSplit.length == 1) {
                throw new DrBrownException(Ui.getTodoExceptionNoPriority());
            }

            Task todo = new Todo(false, todoSplit[0].trim(), Task.Priority.valueOf(todoSplit[1].trim()));

            return new AddCommand(todo);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            throw new DrBrownException(Ui.getTodoExceptionOthers());
        }
    }

}
