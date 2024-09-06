package lutchat;

import java.util.ArrayList;

public class Ui {
    private String lastResponse;

    public Ui() {
    }

    /**
     * Retrieves the last response stored in the UI.
     *
     * @return The last response.
     */
    public String getLastResponse() {
        return lastResponse;
    }

    public void greet() {
        lastResponse = "Hello! I'm Lutchat!\nWhat can I do for you?";
    }

    public String exit() {
        lastResponse = "Bye! Hope to see you again soon!";
        return lastResponse;
    }

    public void showError(String message) {
        lastResponse = "OOPS!!! " + message;
    }

    public void showTaskList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder("Here are the task(s) in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        lastResponse = sb.toString();
    }

    public void showTaskAdded(Task task, int size) {
        lastResponse = "Got it. I've added this task:\n" + task + "\nNow you have " + size + " task(s) in the list.";
    }

    public void showTaskRemoved(Task task, int size) {
        lastResponse = "Noted. I've removed this task:\n" + task + "\nNow you have " + size + " task(s) in the list.";
    }

    public void showTaskMarked(Task task) {
        lastResponse = "Nice! I've marked this task as done:\n" + task;
    }

    public void showTaskUnmarked(Task task) {
        lastResponse = "OK, I've marked this task as not done yet:\n" + task;
    }

    public void showResponse(String message) {
        lastResponse = message;
    }
}
