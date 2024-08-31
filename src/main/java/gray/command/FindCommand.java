package gray.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import gray.GrayException;
import gray.TaskList;
import gray.Ui;
import gray.task.Task;

/**
 * A command that finds tasks.
 */
public class FindCommand extends Command {
    private final String search;

    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public void execute(Ui ui, TaskList tasks) throws GrayException {
        List<Task> matchingTasks = tasks.search(search);
        if (matchingTasks.isEmpty()) {
            ui.say("No matches found");
            return;
        }
        String matches = String.join("\n",
                IntStream.range(0, matchingTasks.size())
                        .mapToObj(i -> String.format(
                                "%d. %s",
                                i + 1,
                                matchingTasks.get(i)))
                        .collect(Collectors.toList()));
        ui.say(String.format(
                "Here are the matching tasks in your list:\n%s",
                matches));
    }
}
