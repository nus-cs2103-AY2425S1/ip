package executable;

import java.util.ArrayList;

import task.Task;

/**
 * An executable for listing out tasks.
 *
 * @author Toh Yi Hui A0259080A
 */
public class ListTask extends TaskModifier {
    /**
     * Constructor for a new ListTask executable.
     */
    public ListTask() {
        super();
    }

    /**
     * Constructor for a new ListTask executable.
     *
     * @param tasks the tasks to modify.
     */
    public ListTask(ArrayList<Task> tasks) {
        super(tasks);
    }

    /**
     * Output the list of tasks in a nice format.
     *
     * @return 0 normally, 2 if tasks == null.
     */
    @Override
    public int execute() {
        if (tasks == null) {
            super.output("Task list cannot be null.");
            return 2;
        }
        
        String output = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            output += "\n" + (i + 1) + ". " + task.toString();
        }
        super.output(output);
        return 0;
    }
}
