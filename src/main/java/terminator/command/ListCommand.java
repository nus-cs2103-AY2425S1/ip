package terminator.command;

import java.util.ArrayList;
import java.util.stream.IntStream;

import javafx.util.Pair;
import terminator.task.Task;

/**
 * Concrete class representing a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    private static final String ERR_MSG = """
            List command takes no arguments.\n
            Usage: list""";
    public ListCommand(String input) {
        super(input);
    }

    /**
     * Lists all the tasks in the task list.
     *
     * @param todoList The task list.
     * @throws TerminatorException if the user supplies extra parameters with the {@code list} command.
     */
    @Override
    public String execute(ArrayList<Task> todoList) throws TerminatorException {
        if (!(input == null)) {
            throw new TerminatorException(ERR_MSG);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Listing current mission objectives:\n");

        if (!todoList.isEmpty()) {
            IntStream
                    .range(1, todoList.size() + 1)
                    .boxed()
                    .map(i -> new Pair<Integer, Task>(i, todoList.get(i - 1)))
                    .forEach(pair -> sb.append("\n" + pair.getKey() + "." + pair.getValue()));
        }

        return sb.toString();
    }
}
