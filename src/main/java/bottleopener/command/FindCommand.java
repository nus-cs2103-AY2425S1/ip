package bottleopener.command;

import bottleopener.task.Task;
import bottleopener.task.Tasklist;
import bottleopener.ui.Ui;

/**
 * The {@code FindCommand} class handles searching tasks in the task list that match a given keyword.
 * It iterates through the task list and returns tasks whose descriptions contain the keyword.
 */
public class FindCommand implements Command {
    private final Tasklist tasklist;
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} object with the specified task list and keyword for searching tasks.
     *
     * @param tasklist The list of tasks to search through.
     * @param keyword The keyword to match against task descriptions.
     */
    public FindCommand(Tasklist tasklist, String keyword) {
        this.tasklist = tasklist;
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks whose descriptions contain the given keyword.
     * It builds and returns a formatted string of matched tasks.
     *
     * @return A string containing the list of tasks that match the keyword, wrapped with a spacer for display.
     */
    @Override
    public String runCommand() {
        StringBuilder findOutput = new StringBuilder();
        int count = 1;
        for (int i = 0; i < tasklist.getSize(); i++) {
            Task curr = tasklist.getTask(i);
            String taskDescription = curr.getDescription().trim().toLowerCase();
            if (taskDescription.contains(keyword)) {
                findOutput.append(String.format("%d. %s%n", count, curr));
                count++;
            }
        }
        return Ui.wrapSpacer(Ui.showFoundTasks() + findOutput);
    }
}
