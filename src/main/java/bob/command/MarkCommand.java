package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.IncorrectArgumentException;
import bob.exception.MissingArgumentException;

import java.util.Map;

public class MarkCommand extends Command {
    public static final String COMMAND = "mark";

    public MarkCommand(Map<String, String> arguments) {
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
            throw new MissingArgumentException("index of the task that you want to mark");
        }

        int index;
        try {
            index = Integer.parseInt(argument) - 1;
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException("an integer");
        }
        if (index < 0 || index >= tasks.size()) {
            throw new IncorrectArgumentException("a valid index");
        }

        tasks.get(index).mark();
        ui.printWithFormat("Nice! I've marked this task as done:\n"
                + tasks.get(index));
    }
}
