package hue.command;
import hue.Hue;
import hue.HueException;
import hue.UI.UI;
import hue.storage.Storage;
import hue.task.Task;
import hue.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;
public class FindCommand extends Command {
    private final String input;
    /**
     * Constructs a {@code FindCommand} with the given input string.
     *
     * @param input The full command string input by the user.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws HueException {
        String keyword = this.input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new HueException("The keyword for the find command cannot be empty!");
        }
        ArrayList<Task> result = new ArrayList<>();

        for (Task task : tasks.getTasks()) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                result.add(task);
            }
        }
        if (result.isEmpty()) {
            ui.showError("No tasks found that matches the keyword: " + keyword);
        } else {
            ui.showMatchingTasks(result, keyword);
        }
    }
}