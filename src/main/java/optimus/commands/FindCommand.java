package optimus.commands;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import optimus.Storage;
import optimus.TaskList;
import optimus.exceptions.OptimusExceptions;
import optimus.tasks.Task;

/**
 * Command to filter tasks by keyword
 */
public class FindCommand extends Command {
    private final String filter;

    public FindCommand(String filter) {
        this.filter = filter;
    }

    /**
     * @param storage permanent storage
     * @param tasks session storage
     * @throws OptimusExceptions
     */

    @Override
    public String execute(Storage storage, TaskList tasks) throws OptimusExceptions {
        Stream<Task> filteredTasks = tasks.filterByKeyword(filter);
        String result = "Here are the matching tasks in your list:\n";

        result += filteredTasks
                .map(Task::toString)
                .collect(Collectors.joining("\n"));

        return result;
    }
}
