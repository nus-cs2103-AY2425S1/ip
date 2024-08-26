package alice.command;

import java.util.List;

import alice.storage.TaskList;
import alice.task.Task;
import alice.ui.Ui;

public class ListTask extends Command {
    public ListTask(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    @Override
    public void execute(String line) {
        List<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            ui.say("You have no tasks.");
        } else {
            String[] lines = new String[tasks.size() + 1];
            lines[0] = "These are your tasks:";
            for (int i = 0; i < tasks.size(); i++) {
                lines[i + 1] = String.format("%d. %s", i + 1, tasks.get(i));
            }
            ui.say(lines);
        }
    }
}