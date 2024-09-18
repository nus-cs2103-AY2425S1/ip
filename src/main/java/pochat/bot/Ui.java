package pochat.bot;

import pochat.tasks.Task;

/**
 * Handles returning the right output to the user for each scenario
 */
class Ui {
    /**
     * Returns goodbye message for when user enters "bye"
     * @return goodbye message
     */
    public String getGoodbyeMessage() {
        return "Bye! Hope to see you again soon :)";
    }

    /**
     * Returns the message to be displayed when user adds a tasks
     * @param task that is added
     * @param numTasks that the user now has
     * @return message to be displayed after adding task
     */
    public String getAddTaskMessage(Task task, int numTasks) {
        return "Got it. I've added this task:\n" + task + "\nNow you have "
                + numTasks + " tasks in the list.";

    }

    /**
     * Returns the tasks stored in the chatbot history
     * @param taskList of tasks stored in chatbot history
     * @return list of tasks stored in the chatbot history as a String
     */
    public String getListOfTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "There are no tasks in the list! Add some tasks to get started :)";
        }

        StringBuilder message = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            message.append((i + 1) + ". " + taskList.get(i) + "\n");
        }

        return message.toString();
    }

    /**
     * Returns the message to be sent after marking task as done
     * @param task to be marked as done
     * @return message to be sent after marking task as done
     */
    public String getMarkTaskDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Returns the message to be sent after marking task as undone
     * @param task to be marked as undone
     * @return message to be sent after marking task as undone
     */
    public String getMarkTaskUndoneMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Returns the message to be sent to the user for an invalid input
     * @return message to be sent to the user for invalid input
     */
    public String getInvalidInputMessage() {
        return "Please enter a valid input and try again! Some examples of valid inputs are:\n"
                + "todo [description]\ndeadline [description] /by [dd/mm/yyyy HHMM]\n"
                + "event [description] /from [dd/mm/yyyy HHMM] /to [dd/mm/yyyy HHMM]\n"
                + "For more commands, refer to the project's README";
    }

    /**
     * Returns the message to be sent to the user for an invalid index
     * @return message to be sent to the user for invalid index
     */
    public String getInvalidIndexMessage() {
        return "Task index out of range! Please enter a valid task index";
    }

    /**
     * Returns the message to be sent to the user after deleting the specified task
     * @param task to be deleted
     * @param numTasks after deletion
     * @return message to be sent to the user after deleting task
     */
    public String getDeleteTaskMessage(Task task, int numTasks) {
        return "Noted. I've removed this task:\n" + task + "\nNow you have "
                + numTasks + " tasks in the list.";
    }

    /**
     * Returns the message to be sent when a duplicate task is added
     * @return message to be sent when duplicate task is added
     */
    public String getDuplicateTaskMessage() {
        return "Sorry! You already have this task in your list. Please try adding "
                + "another task";
    }

    /**
     * Returns the list of tasks matching the keyword
     * @param keyword to search for
     * @param taskList of tasks in the chatbot history
     * @return list of tasks matching the keyword
     */
    public String findMatchingTasks(String keyword, TaskList taskList) {
        return taskList.findMatchingTasks(keyword);
    }
}
