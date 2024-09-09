package handsome;

import java.util.ArrayList;

import handsome.task.Task;

/**
 * The Ui class handles user interaction with the Handsome chatBot.
 * It manages input reading and displays various messages to the user, including
 * greetings, errors, and task updates.
 */
public class Ui {
    /**
     * Greets the user when the Handsome chatBot starts.
     */
    public String greet() {
        String greetText = """
                Hello! I'm Handsome
                What can I do for you?""";
        System.out.println(greetText);
        return greetText;
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be shown.
     */
    public String showError(String errorMessage) {
        System.out.println(errorMessage);
        return errorMessage;
    }

    /**
     * Displays a goodbye message when the user exits the chatBot.
     */
    public String showByeText() {
        System.out.println("Bye. Hope to see you again soon!");
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a message indicating that the task at the specified index has been marked as done.
     *
     * @param index The index of the task that was marked as done.
     * @param tasks The TaskList containing the tasks.
     */
    public String showMarkText(int index, TaskList tasks) {
        String markedTask = "Nice! I've marked this task as done:\n" + tasks.getStringOfIndex(index);
        System.out.println(markedTask);
        return markedTask;
    }

    /**
     * Displays a message indicating that the task at the specified index has been marked as not done.
     *
     * @param index The index of the task that was marked as not done.
     * @param tasks The TaskList containing the tasks.
     */
    public String showUnmarkText(int index, TaskList tasks) {
        String unmarkedTask = "OK, I've marked this task as not done yet:\n" + tasks.getStringOfIndex(index);
        System.out.println(unmarkedTask);
        return unmarkedTask;
    }

    /**
     * Displays a message indicating that the task at the specified index has been deleted.
     *
     * @param index The index of the task that was deleted.
     * @param tasks The TaskList containing the tasks.
     */
    public String showDeleteText(int index, TaskList tasks) {
        String deletedTask = "Noted. I've removed this task:\n" + tasks.getStringOfIndex(index);
        System.out.println(deletedTask);
        return deletedTask;
    }

    /**
     * Displays all tasks currently in the task list.
     * If there are no tasks, an appropriate message is displayed instead.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public String showList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("There is currently nothing in your list!");
            return "There is currently nothing in your list!";
        }
        StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            int count = i + 1;
            stringBuilder.append(count).append(".").append(tasks.getStringOfIndex(i)).append("\n");
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    /**
     * Displays the list of available commands that the user can use.
     */
    public String showPrompt() {
        String prompt = "1. todo <description>: Add a todo task\n"
                + "2. deadline <description> /by <date> (date format: yyyy-mm-dd <time in 24 hr format>)"
                + ": Add a deadline task\n"
                + "3. event <description> /from <date> /to <date> (date format: yyyy-mm-dd <time in 24 hr format>)"
                + ": Add an event task\n"
                + "4. mark <task index>: Mark <index> task as done\n"
                + "5. unmark <task index>: Mark <index> task as not done\n"
                + "6. delete <task index>: Delete <index> task from tasks list"
                + "7. list: Show the current tasks list\n"
                + "8. prompt: Show the available commands\n"
                + "9. bye : Exit and close the chat bot";
        System.out.println(prompt);
        return prompt;
    }

    /**
     * Displays a message indicating that a new task has been added to the list.
     *
     * @param tasks The TaskList containing the tasks, including the newly added task.
     */
    public String showTaskText(TaskList tasks) {
        String tasksText = "Got it. I've added this task:\n" + tasks.getStringOfIndex(tasks.getSize() - 1)
                + "\n\"Now you have " + tasks.getSize() + " tasks in the list.";
        System.out.println(tasksText);
        return tasksText;
    }

    /**
     * Displays the tasks that match a specified keyword to the user.
     * If there are no matching tasks, an appropriate message is displayed. Otherwise,
     * the matching tasks are listed with their corresponding indices.
     *
     * @param tasks An {@code ArrayList<Task>} containing the tasks that match the search keyword.
     */
    public String showMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("There are no matching tasks in your list!");
            return "There are no matching tasks in your list!";
        }
        StringBuilder stringBuilder = new StringBuilder("Here are the matching tasks in your list:\n");
        int i = 1;
        for (Task t : tasks) {
            stringBuilder.append(i).append(".").append(t.toString()).append("\n");
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }
}
