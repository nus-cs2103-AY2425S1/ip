package applemazer;

import tasks.Task;

/**
 * Class that handles chatbot messages.
 */
public class Ui {
    /**
     * Returns a predefined greeting message.
     */
    public String greeting() {
        return "Hello! I'm Applemazer.\nWhat can I do for you?\n";
    }

    /**
     * Returns a predefined farewell message.
     */
    public String farewell() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Lists all tasks in the given {@code TaskList}.
     * @param tasks The {@code TaskList} that contains the tasks to list.
     * @return A {@code String} listing all tasks in the given {@code TaskList}.
     */
    public String getFullTaskListString(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            sb.append(i + 1).append(". ").append(task.getStatusIcon()).append(task).append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Lists all tasks in the given {@code TaskList} that match or contain the provided {@code desc}.
     * @param tasks The {@code TaskList} that contains the tasks to search through.
     * @param desc The {@code String} to search for within each task.
     * @return A {@code String} listing all tasks in the given {@code TaskList} that match or contain
     *         the provided {@code desc}.
     */
    public String getMatchingTaskListString(TaskList tasks, String desc) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int taskNumber = 1;
        for (Task task : tasks.getList()) {
            if (task.getDescription().contains(desc)) {
                sb.append(taskNumber).append(". ").append(task.getStatusIcon()).append(task);
            }
            taskNumber++;
        }
        return sb.toString();
    }

    /**
     * Returns the predefined message that indicates when a {@code Task} is marked as done.
     */
    public String getTaskSetDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n"
                + "    " + task.getStatusIcon() + task + "\n";
    }

    /**
     * Returns the predefined message that indicates when a {@code Task} is marked as not done.
     */
    public String getTaskSetUndoneMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + "    " + task.getStatusIcon() + task + "\n";
    }

    /**
     * Returns the predefined message that indicates when a {@code Task} has been added to the
     * {@code TaskList}.
     */
    public String getTaskAddedMessage(Task task, int size) {
        return "Got it. I've added this task:\n"
                + "    " + task.getStatusIcon() + task + "\n"
                + "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Returns the predefined message that indicates when a {@code Task} has been deleted from the
     * {@code TaskList}.
     */
    public String getTaskDeletedMessage(Task task, int size) {
        return "Noted. I've removed this task:\n"
                + "    " + task.getStatusIcon() + task + "\n"
                + "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Returns the predefined message that indicates that the current {@code Task} is a duplicate and cannot
     * be added into the {@code TaskList}.
     */
    public String getDuplicateDeny() {
        return "This task is already in the list!\n";
    }
}
