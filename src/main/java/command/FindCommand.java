package command;

import java.util.ArrayList;
import java.util.List;

import tasklist.TaskList;
import tasks.Task;
import ui.CommandLineUI;

public class FindCommand extends Command {
    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;

    }

    public void execute(TaskList tasklist, CommandLineUI ui) {
        List<Task> matches = new ArrayList<Task>();

        List<Task> tasks = tasklist.getTasks();

        for (Task t : tasks) {
            // Add task if matches keyward
            if (t.toString().indexOf(keyword) != -1) {
                matches.add(t);
            }
        }

        // Show matches
        for (int i = 0; i < matches.size(); i++) {
            ui.speakLine((i + 1) + ". " + matches.get(i).toString());
        }

    }

    public boolean isExit() {
        return false;
    }
}
