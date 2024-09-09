package applemazer;

import tasks.Task;

/**
 * Class that handles chatbot messages.
 */
public class Ui {
    /**
     * Constructor for the Ui object.
     */
    public Ui() {}

    /**
     * Greeting message printed when starting up the chatbot.
     */
    public String greeting() {
        return "Hello! I'm Applemazer.\nWhat can I do for you?\n";
    }

    /**
     * Farewell message printed when shutting down the chatbot.
     */
    public String farewell() {
        return "Bye. Hope to see you again soon!";
    }

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

    public String getMatchingTaskListString(TaskList tasks, String desc) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int taskNumber = 1;
        for (Task task : tasks.getList()) {
            if (task.getDescription().contains(desc)) {
                sb.append(taskNumber).append(".").append(task.getStatusIcon()).append(task);
            }
            taskNumber++;
        }
        return sb.toString();
    }

    /**
     * Message printed when task is marked as done.
     */
    public String getTaskSetDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n" +
               "    " + task.getStatusIcon() + task + "\n";
    }

    /**
     * Message printed when task is marked as undone.
     */
    public String getTaskSetUndoneMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" +
               "    " + task.getStatusIcon() + task + "\n";
    }

    /**
     * Message printed when task is added to the task list.
     */
    public String getTaskAddedMessage(Task task, int size) {
        return "Got it. I've added this task:\n" +
               "    " + task.getStatusIcon() + task + "\n" +
               "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Message printed when task is deleted from the task list.
     */
    public String getTaskDeletedMessage(Task task, int size) {
        return "Noted. I've removed this task:\n" +
               "    " + task.getStatusIcon() + task + "\n" +
               "Now you have " + size + " tasks in the list. \n";
    }
}
