package gray.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import gray.GrayException;
import gray.Tasks;
import gray.task.Task;

/**
 * A command that finds tasks.
 */
public class FindCommand implements Command {
    private final String search;

    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public String execute(Tasks tasks) throws GrayException {
        List<Task> matchingTasks = tasks.search(search);
        if (matchingTasks.isEmpty()) {
            return "No matches found";
        }
        String matches = String.join("\n",
                IntStream.range(0, matchingTasks.size())
                        .mapToObj(i -> String.format(
                                "%d. %s",
                                i + 1,
                                matchingTasks.get(i)))
                        .collect(Collectors.toList()));
        return String.format(
                "Here are the matching tasks in your list:\n%s",
                matches);
    }
}
