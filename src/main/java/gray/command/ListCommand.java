package gray.command;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import gray.Tasks;

/**
 * A command that lists tasks.
 */
public class ListCommand implements Command {

    /**
     * Executes the list task command.
     *
     * @param tasks
     * @return
     */
    @Override
    public String execute(Tasks tasks) {
        return IntStream.range(0, tasks.size())
                .mapToObj(i -> String.format("%d. %s", i + 1, tasks.get(i)))
                .collect(Collectors.joining("\n"));
    }
}
