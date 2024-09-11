package greetbot;

import java.util.Scanner;

/**
 * A class which generates string that will be shown to user.
 */
public class Ui {
    private Scanner commandLineReader = new Scanner(System.in);

    /**
     * Returns a welcome message from the chatbot.
     * @return A string for greeting user when the chatbot starts.
     */
    public String greetUser() {
        return String.format("こんにちわ！　私はグリーティングボットです\n"
                + "(Hello! I'm GreetBot)\n"
                + "どんな御用でしょうか\n"
                + "(What can I do for you?)"
        );
    }

    /**
     * Returns a goodbye message from the chatbot.
     * @return A string for saying goodbye to user when user wants to quit.
     */
    public String farewellUser() {
        return String.format("また近いうちにお会いできるのを楽しみにしています！\n"
                + "(I'm looking forward to seeing you again soon!)"
        );
    }

    /**
     * Returns a message to show the task is marked.
     * @param task The specific task.
     * @param total The number of tasks in the current task list.
     * @return The message string.
     */
    public String showMarked(Task task, int total) {
        return String.format("Nice! I've marked this task as done:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.", task.toString(), total);
    }

    /**
     * Returns a message to show the task is unmarked.
     * @param task The specific task.
     * @param total The number of tasks in the current task list.
     * @return The message string.
     */
    public String showUnmarked(Task task, int total) {
        return String.format("OK, I've marked this task as not done yet:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.", task.toString(), total);
    }

    /**
     * Returns a message to show the task is added.
     * @param task The added task.
     * @param total The number of tasks in the current task list.
     * @return The message string.
     */
    public String showAdd(Task task, int total) {
        return String.format("Got it. I've added this task:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.", task.toString(), total);
    }

    public String showDelete(Task task, int total) {
        return String.format("Noted. I've removed this task:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.", task.toString(), total);
    }

    public String showFind(String searchItem, TaskList list) {
        return String.format("Here are the matching tasks in your list:\n"
                + "%s", list.findTasks(searchItem));
    }

    public String readInput() {
        return this.commandLineReader.nextLine();
    }

    public void closeInput() {
        this.commandLineReader.close();
    }

    public String showList(TaskList list) {
        return list.showTasks();
    }
}
