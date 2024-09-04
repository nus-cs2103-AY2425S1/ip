package jeff.command;

import java.util.ArrayList;

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
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        ArrayList<Task> list = tasks.getTasks();
        int counter = 1;
        ui.showMessage("There are the following results that match: ");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i).getDescription().contains(args)) {
                ui.showMessage("" + counter + ". " + tasks.getTask(i));
                counter++;
            }
        }

        if (counter == 1) {
            ui.showMessage("I guess nothing matched...");
        }

    }
}
