package torne.command;

import torne.command.argument.CommandArgument;
import torne.command.argument.CommandArgumentList;
import torne.exception.TorneException;
import torne.task.Task;

import java.util.List;
import java.util.Map;

public class FindTask extends Command {
    protected FindTask() {
        super(
                "find",
                "Finds tasks matching the given keyword",
                new CommandArgumentList(List.of(
                        new CommandArgument("", "Keyword", "Index of task to be deleted")
                ))
        );
    }

    @Override
    public void execute(Map<String, String> arguments) throws TorneException {
        String keyword = arguments.get("");

        List<Task> tasksFound = taskHandler.findTasksWithKeyword(keyword);

        StringBuilder messageBuilder = new StringBuilder();

        if (tasksFound.isEmpty()) {
            // empty
            messageBuilder.append(String.format("Your search for keyword '%s' returned no results :(", keyword));
            output.writeText(messageBuilder.toString().trim());
            return;
        }

        messageBuilder.append(String.format("Your search for '%s' returned the following tasks:\n", keyword));
        // TODO maybe the index should be one that taskHandler assigns to the tasks...
        for (int i = 0; i < tasksFound.size(); i++) {
            String itemized = String.format("%d. %s\n", i + 1, tasksFound.get(i));
            messageBuilder.append(itemized);
        }

        output.writeText(messageBuilder.toString().trim());
    }
}
