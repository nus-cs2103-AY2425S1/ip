package luna.command;

import java.util.ArrayList;

import luna.Storage;
import luna.TaskList;
import luna.task.Task;

/**
 * Represents a command to find tasks from current list of tasks.
 */
public class FindCommand extends Command {
    private final String query;

    /**
     * Creates command to search list of tasks.
     *
     * @param query Description of task to search for.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        ArrayList<Task> task = tasks.find(query);

        if (task.isEmpty()) {
            System.out.println("No task with matching description");
        } else {
            System.out.println("Here are the tasks with the matching description:");
            for (int i = 0; i < task.size(); i++) {
                String s = String.format("%d.%s", i + 1 , task.get(i));
                System.out.println(s);
            }
        }
    }
}
