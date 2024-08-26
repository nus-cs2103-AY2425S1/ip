package luna.command;

import luna.Storage;
import luna.TaskList;
import luna.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String query;

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
