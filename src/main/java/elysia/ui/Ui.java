package elysia.ui;

import elysia.task.Task;

import java.util.ArrayList;

/**
 * Text UI of the application.
 */
public class Ui {
    private static String line = "____________________________________________________________";
    private static String welcomeMessage = "Hi there! Did you miss me?\n" +
            "Wherever you are and whenever you need,\n" +
            "Elysia will always meet your expectations.";
    private static String exitMessage = "Alright, this time we really have to say goodbye.\n" +
            "Goodbye, Mei!";




    public String getWelcomeMessage() {
        StringBuilder result = new StringBuilder();
        result.append(welcomeMessage).append("\n");
        return result.toString();
    }

    /**
     * Displays added message
     * @param task
     */
    public String getAddedMessage(ArrayList<Task> arrayLists, Task task) {
        StringBuilder result = new StringBuilder();

        result.append("Got it. I've added this task:\n");
        result.append("  ").append(task).append("\n");
        result.append("Now you have ").append(arrayLists.size()).append(" tasks in the list.\n");

        return result.toString();
    }

    public String getExitMessage() {
        return exitMessage;
    }



    public String printList(ArrayList<Task> arrayLists) {
        StringBuilder result = new StringBuilder();
        int n = arrayLists.size();
        result.append("Here are the tasks in your list: \n");
        for (int i = 1; i <= n; i++) {
            Task curr = arrayLists.get(i - 1);
            result.append(i + "." + curr.toString()).append("\n");
        }
        return result.toString();
    }

    public String getMarkAsDoneMessage(Task task) {
        StringBuilder result =  new StringBuilder();
        result.append("Nice! I've marked this task as done:\n");
        result.append(task);
        return result.toString();
    }

    public String getUnmarkAsDoneMessage(Task task) {
        StringBuilder result = new StringBuilder();
        result.append("OK, I've marked this task as not done yet:\n");
        result.append(task);
        return result.toString();
    }


    public String getDeleteTaskMessage(ArrayList<Task> arrayLists, Task task) {
        StringBuilder result = new StringBuilder();
        result.append("Noted. I've removed this task:\n");
        result.append(" ").append(task).append("\n");
        result.append("Now you have " + arrayLists.size() + " tasks in the list.");
        return result.toString();
    }

}
