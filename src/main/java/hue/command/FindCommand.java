package hue.command;
import hue.HueException;
import hue.ui.ui;
import hue.storage.Storage;
import hue.task.Task;
import hue.task.TaskList;

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
    public String execute(TaskList tasks, ui ui, Storage storage) throws HueException {
        String keyword = this.input.substring(5).trim();
        assert !keyword.isEmpty() : "Keyword should not be empty";

        ArrayList<Task> result = new ArrayList<>();

        for (Task task : tasks.getTasks()) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                result.add(task);
            }
        }

        return result.isEmpty()
                ? ui.noMatchingTasks(keyword)
                : ui.showMatchingTasks(result, keyword);
    }
}
