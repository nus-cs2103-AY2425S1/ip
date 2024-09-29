package pixy.ui;

import java.util.List;

import pixy.tasks.Task;

/**
 * Handles the user interaction with the chatbot.
 */
public class Ui {

    /**
     * Shows the welcome message from chatbot at the beginning.
     *
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Pixy.\nWhat can I do for you?\n");
    }

    public String showTasks(List<Task> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            sb.append((i + 1) + ". " + task.toString() + "\n");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * Displays the tasks which match the keyword inputted by user.
     *
     * @param matchedTasks List of matched Tasks.
     */
    public String showMatchedTasks(List<Task> matchedTasks) {
        StringBuilder sb = new StringBuilder();
        if (matchedTasks.isEmpty()) {
            sb.append("No tasks found with matching description.\n");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchedTasks.size(); i++) {
                Task task = matchedTasks.get(i);
                sb.append((i + 1) + ". " + task.toString() + "\n");
            }
        }
        return sb.toString();
    }

    /**
     * Displays the goodbye message.
     *
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the appropriate message when an error is encountered.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
