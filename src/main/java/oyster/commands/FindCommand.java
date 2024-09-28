package oyster.commands;

import oyster.LogicController;
import oyster.tasks.Task;
import oyster.tasks.TaskList;

/**
 * FindCommand finds Tasks using a keyword when executed.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.trim().toLowerCase();
    }

    @Override
    public void execute() {
        TaskList taskList = LogicController.getTaskList();

        TaskList resultTaskList = new TaskList();

        for (Task task : taskList.getItems()) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                resultTaskList.insert(task);
            }
        }

        if (resultTaskList.isEmpty()) {
            setMessage("Oops, nothing found for '" + keyword + "'!");
        } else {
            setMessage(new String[] {
                "Tasks matching '" + keyword + "':",
                resultTaskList.toString()
            });
        }
    }
}
