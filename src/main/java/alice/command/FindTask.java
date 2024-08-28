package alice.command;

import java.util.List;

import alice.storage.TaskList;
import alice.task.Task;
import alice.ui.Ui;

public class FindTask extends Command {
    public FindTask(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    /**
     * Finds tasks which contain a given keyword.
     * 
     * @param  line the input line from the user
     */
    @Override
    public void execute(String line) {
        String[] tokens = line.split(" ", 2);
        if (tokens.length != 2) {
            ui.warn("Missing keyword. Usage: find <keyword>");
            return;
        }

        String keyword = tokens[1];
        List<Task> matches = taskList.findTasks(keyword);
        String[] lines = new String[matches.size() + 1];
        if (matches.isEmpty()) {
            lines[0] = String.format("No tasks contains the keyword \"%s\".", keyword);
        } else {
            lines[0] = "Here are the matching tasks in your list:";
            for (int i = 0; i < matches.size(); i++) {
                lines[i + 1] = matches.get(i).toString();
            }
        }
        ui.say(lines);
    }
}