package yihuibot.executable;

import yihuibot.task.TaskList;

/**
 * A Executable for filtering out tasks.
 *
 * @author Toh Yi Hui A0259080A
 */
public class FilterTask extends TaskModifier {
    private String output;
    private String filter;

    /**
     * Constructor for a new FilterTask Executable.
     *
     * @param filter the String to filter the Task with.
     */
    public FilterTask(String filter) {
        super();
        this.filter = filter;
    }

    /**
     * Output the list of Tasks after filtering, in a nice format.
     * Informs if there are no Tasks after filtering.
     *
     * @return false.
     * @throws NullPointerException when TaskList is null.
     */
    @Override
    public boolean execute() throws NullPointerException {
        if (super.tasks == null) {
            throw new NullPointerException("TaskList cannot be null.");
        }

        TaskList filtered = filter(super.tasks, filter);
        if (filtered.size() == 0) {
            output = "No tasks found with the given filter.";
        } else {
            output = "Here are the tasks with matching description:\n" + filtered.toString();
        }
        return false;
    }

    /**
     * Return the output after executing the Executable.
     *
     * @return the output of the Executable.
     */
    public String getOutput() {
        return output;
    }

    /**
     * Filter the given TaskList based on the filter. Returns only Tasks
     * with description that contains the filter. Does not modify the
     * original TaskList.
     *
     * @param tasks the TaskList to filter.
     * @param filter the String to filter the Tasks.
     * @return a TaskList of filtered Tasks.
     */
    private TaskList filter(TaskList tasks, String filter) {
        return new TaskList(tasks.stream()
                .filter(task -> task.getDescription().contains(filter)));
    }
}
