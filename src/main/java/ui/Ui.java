package ui;

import tasks.Task;

import java.util.ArrayList;

public class Ui {

    /**
     * Pad a provided message with horizontal lines and indentation to separate bot input from user input
     * @param msg - Message the bot should return to the user
     * @return Standard bot message formatted message, easily distinguishable from user messages
     */
    private static String padMessage(String msg) {
        // first stage - add horizontal lines above and below the message
        String horizontalLine = "_".repeat(80);
        String msgWithHLines = String.join("\n", horizontalLine, msg, horizontalLine);

        // second stage - provide 1 indentation to all lines of the message, including the horizontal line
        String indentSpaces = " ".repeat(4);

        return indentSpaces.concat(msgWithHLines.replace("\n", "\n".concat(indentSpaces)));
    }

    // Provide information on added task and total number of tasks for the user
    private static String addTaskMessage(Task t, ArrayList<Task> tasks) {
        return "Got it. I've added this task:\n  " + t + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    public void printGenericMessage(String s) {
        System.out.println(padMessage(s));
    }

    public void printWelcomeMessage() {
        System.out.println(padMessage("Hello! I'm Grok\nWhat ya wanna do to grok your way to success?"));
    }

    public Ui() {

    }
}
