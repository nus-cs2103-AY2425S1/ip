package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.IncorrectArgumentException;
import bob.exception.MissingArgumentException;

import java.util.Map;

public class UntagCommand extends Command {
    public static final String COMMAND = "untag";

    public UntagCommand(Map<String, String> arguments) {
        super(arguments);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String argument = arguments.get("");
        if (argument == null || argument.isBlank()) {
            throw new MissingArgumentException("index of the task that you want to untag");
        }

        String[] args = argument.split(" ", 2);

        int index;
        try {
            index = Integer.parseInt(args[0]) - 1;
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException("an integer");
        }
        if (index < 0 || index >= tasks.size()) {
            throw new IncorrectArgumentException("a valid index");
        }

        if (args.length == 1) {
            tasks.unTag(index);
            ui.printWithFormat("OK, I've removed all tags from this task:\n"
                    + tasks.get(index));
        } else {
            boolean previouslyTagged = tasks.unTag(index, args[1]);
            if (previouslyTagged) {
                ui.printWithFormat("OK, I've removed tag #" + args[1] + " from this task:\n"
                        + tasks.get(index));
            } else {
                ui.printWithFormat("Looks like you have not tagged this task with #" + args[1] + ":\n"
                        + tasks.get(index));
            }
        }
    }
}
