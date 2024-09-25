package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.IncorrectArgumentException;
import bob.exception.MissingArgumentException;
import bob.task.Task;

import java.util.Map;

public class TagCommand extends Command {
    public static final String COMMAND = "tag";

    public TagCommand(Map<String, String> arguments) {
        super(arguments);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String argument = this.arguments.get("");
        String[] args = argument.split(" ", 3);

        int index;
        try {
            index = Integer.parseInt(args[0]) - 1;
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException("an integer for the task index");
        }
        if (index < 0 || index >= tasks.size()) {
            throw new IncorrectArgumentException("a valid index");
        }

        if (args.length < 2) {
            throw new MissingArgumentException("name of the tag");
        }
        if (args.length > 2) {
            throw new IncorrectArgumentException("the tag name WITHOUT spaces");
        }
        String tagName = args[1];

        Task task = tasks.get(index);
        boolean tagged = task.tag(tagName);
        if (tagged) {
            ui.printWithFormat("OK, I've tagged this task with #" + tagName + ":\n"
                    + task);
        } else {
            ui.printWithFormat("Looks like you've already tagged this task with #" + tagName + ":\n"
                    + task);
        }
    }
}
