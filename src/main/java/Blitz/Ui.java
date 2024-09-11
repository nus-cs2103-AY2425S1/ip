package blitz;

import task.Task;

/**
 * Provides methods for generating UI strings related to Task.
 */
public class Ui {
    /**
     * Generates a String message indicating that a task has been added.
     *
     * @param size Size of the current TaskList.
     * @param task Task object that has been added.
     * @return String message indicating that a task has been added.
     */
    public String getStringForTaskAdded(int size, Task task) {
        return "Got it. I've added this task:\n"
                + "  [" + task.getType() + "][ ] " + task + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Generates a String message indicating that a task has been deleted.
     *
     * @param task Task object that has been deleted.
     * @return String message indicating that a task has been deleted.
     */
    public String getStringForTaskDeleted(Task task) {
        return "Noted. I've removed this task:\n"
                + "  [" + task.getType() + "]" + "[" + (task.isDone() ? "X" : " ") + "] " + task;
    }

    /**
     * Generates a String message indicating that a task has been marked.
     *
     * @param task Task object that has been marked.
     * @return String message indicating that a task has been marked.
     */
    public String getStringForTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n"
                + "  [" + task.getType() + "]" + "[X] " + task;
    }

    /**
     * Generates a String message indicating that a task has been unmarked.
     *
     * @param task Task object that has been unmarked.
     * @return String message indicating that a task has been unmarked.
     */
    public String getStringForTaskUnmarked(Task task) {
        return "Ok, I've marked this task as not done yet\n:"
                + "  [" + task.getType() + "]" + "[ ] " + task;
    }

    /**
     * Generates a String message listing all tasks that are matched.
     *
     * @param matchedTaskList TaskList containing all the matched tasks.
     * @return String message listing all matched tasks.
     */
    public String getStringForTasksMatched(TaskList matchedTaskList) {
        String string = "Here are the matching tasks in your list:\n";

        for (int i = 0; i < matchedTaskList.getSize(); i++) {
            Task curr = matchedTaskList.getTask(i);

            assert curr != null : "Task in task list must not be null";

            string += "    " + (i + 1) + ".[" + curr.getType() + "]"
                    + "[" + (curr.getStatus() ? "X" : " ") + "] " + curr + "\n";
        }

        return string;
    }

    /**
     * Generates a String message listing all tasks.
     *
     * @param taskList TaskList containing all the tasks.
     * @return String message listing all the tasks.
     */
    public String getStringForAllTasks(TaskList taskList) {
        String string = "Here are the tasks in your list:\n";

        for (int i = 0; i < taskList.getSize(); i++) {
            Task curr = taskList.getTask(i);

            assert curr != null : "Task in task list must not be null";

            string += "    " + (i + 1) + ".[" + curr.getType() + "]"
                    + "[" + (curr.isDone() ? "X" : " ") + "] " + curr + "\n";
        }

        return string;
    }
}
