package blacknut.ui;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles user interaction by displaying messages and reading input.
 */
class Ui {
    private static final String LOGO = " ____  _            _                _   \n"
            + "|  _ \\| |          | |              | |  \n"
            + "| |_) | | __ _  ___| | ___ __  _   _| |_ \n"
            + "|  _ <| |/ _ |/ __| |/ / '_ \\| | | | __|\n"
            + "| |_) | | (_| | (__|   <| | | | |_| | |_ \n"
            + "|____/|_|\\__,_|\\___|_|\\_\\_| |_|\\__,_|\\__|\n";

    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Blacknut");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public String getWelcomeMessage() {
        return "Hello from\n" + LOGO
                + "\n____________________________________________________________\n"
                + " Hello! I'm Blacknut\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
    }

    public String getGoodbyeMessage() {
        return " Bye. Hope to see you again soon!";
    }

    public String getTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return " The list is empty.";
        }
        StringBuilder sb = new StringBuilder(" Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    public String getMarkedTask(Task task, boolean markAsDone) {
        if (markAsDone) {
            return " Nice! I've marked this task as done:\n   " + task;
        } else {
            return " OK, I've marked this task as not done yet:\n   " + task;
        }
    }

    public String getAddedTask(Task task, int totalTasks) {
        return " Got it. I've added this task:\n   " + task + "\n Now you have " + totalTasks + " tasks in the list.";
    }

    public String getDeletedTask(Task task, int totalTasks) {
        return " Noted. I've removed this task:\n   " + task + "\n Now you have " + totalTasks + " tasks in the list.";
    }

    public String getMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return " No tasks match your search.";
        }
        StringBuilder sb = new StringBuilder(" Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(matchingTasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    public String getError(String message) {
        return " ☹ OOPS!!! " + message;
    }

    public String getLine() {
        return "____________________________________________________________";
    }

}
