package ui;

import tasks.Task;

import java.util.ArrayList;

public class Ui {
    public Ui() {

    }

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

    public void printGenericMessage(String s) {
        System.out.println(padMessage(s));
    }

    public void printWelcomeMessage() {
        printGenericMessage("Hello! I'm Grok\nWhat ya wanna do to grok your way to success?");
    }

    public void printByeMessage() {
        printGenericMessage("Bye. Hope to see you again soon!");
    }

    public void printErrorMessage(String s) {
        printGenericMessage("Error! " + s);
    }
}
