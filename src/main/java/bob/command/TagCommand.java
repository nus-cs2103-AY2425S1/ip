package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.IncorrectArgumentException;
import bob.exception.MissingArgumentException;

import java.util.List;
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
        if (argument == null) {
            printAllTags(tasks, ui);
        } else {
            tagTask(tasks, ui, argument);
        }
    }

    private void printAllTags(TaskList tasks, Ui ui) {
        String[] allTags = tasks.getAllTags().toArray(new String[0]);
        if (allTags.length == 0) {
            ui.printWithFormat("You have not tagged any tasks yet.");
            return;
        }

        StringBuilder str = new StringBuilder();
        for (String tagName : allTags) {
            str.append("#").append(tagName).append(":\n");
            List<Integer> indices = tasks.getIndicesTaggedWith(tagName);
            indices.forEach(index -> str.append("  ")
                                        .append(index + 1)
                                        .append(". ")
                                        .append(tasks.get(index))
                                        .append("\n"));
        }

        ui.printWithFormat(str.toString());
    }

    private void tagTask(TaskList tasks, Ui ui, String argument) {
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

        boolean tagged = tasks.tag(index, tagName);
        if (tagged) {
            ui.printWithFormat("OK, I've tagged this task with #" + tagName + ":\n"
                    + tasks.get(index));
        } else {
            ui.printWithFormat("Looks like you've already tagged this task with #" + tagName + ":\n"
                    + tasks.get(index));
        }
    }
}
