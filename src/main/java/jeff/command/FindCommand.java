package jeff.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import jeff.Storage;
import jeff.TaskList;
import jeff.Ui;
import jeff.exceptions.JeffException;
import jeff.task.Task;

/**
 * Represents a command that searches for tasks based on a provided search term.
 * This command filters the task list to find entries where the task description contains
 * the given search term. It is initialized with a non-empty search term.
 *
 * <p>The command will display all matching tasks, or a message indicating no matches
 * were found if there are no relevant results.</p>
 */
public class FindCommand extends Command {
    private String args;

    /**
     * Constructs a {@code FindCommand} with the specified search term.
     *
     * @param args the search term used to find matching tasks. It must not be empty.
     * @throws JeffException if {@code args} is empty, indicating that no search term was provided.
     */
    public FindCommand(String args) throws JeffException {
        super();
        if (args.isEmpty()) {
            throw new JeffException("You must provide a search term!");
        }
        this.args = args.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        ArrayList<Task> list = tasks.getTasks();

        // filter the tasks that match the search
        List<Task> matchedList = list.stream()
                .filter(task -> task.getDescription()
                        .toLowerCase()
                        .contains(args.toLowerCase()))
                .toList();
        if (matchedList.isEmpty()) {
            ui.showMessage("There are no matching tasks...");
            return;
        }
        // print out the tasks in the list
        ui.showMessage("The following results match: ");
        IntStream.range(0, matchedList.size())
                .forEach(i -> ui.showMessage((i + 1) + ". " + matchedList.get(i)));
    }
}
