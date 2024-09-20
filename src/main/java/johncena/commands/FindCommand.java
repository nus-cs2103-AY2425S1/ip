package johncena.commands;

import java.util.ArrayList;

import johncena.tasks.Task;

/**
 * The {@code FindCommand} class implements the {@code Command} interface and provides
 * the functionality to execute the "find" command, which searches for tasks that
 * contain a specific keyword in their description.
 */
public class FindCommand implements Command {

    private ArrayList<Task> tasks;
    private String keyword;

    /**
     * Constructs a new {@code FindCommand} with the specified task list and keyword.
     *
     * @param tasks the list of tasks
     * @param keyword the keyword to search for
     */
    public FindCommand(ArrayList<Task> tasks, String keyword) {
        this.tasks = tasks;
        this.keyword = keyword;
    }

    /**
     * Executes the "find" command. Searches for tasks that contain the keyword
     * in their description and prints the matching tasks.
     */
    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        sb.append(" Here are the matching tasks in your list:\n");
        int index = 1;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                sb.append(" ").append(index).append(".").append(task).append("\n");
            }
            index++;
        }
        return sb.toString();
    }
}
