package duke;

import duke.*;

public class Ui {

    /**
     * Returns a Ui object which is used to output
     * to the terminal to communicate with user
     *
     * @param openingText the opening message sent to users.
     * @param closingText the final message sent to users.
     * @return Ui object
     */
    Ui(String openingText, String closingText) {
        this.openingText = openingText;
        this.closingText = closingText;
        this.horizontalLine = horizontalLine = "----------------------------------------------------------";
    }

    /**
     * Returns void, just prints a horizontal line
     */
    void printHorizontalLine() {
        System.out.println(horizontalLine);
    }

    /**
     * Returns void, just prints the opening text
     */
    void printOpening() {
        System.out.println(horizontalLine);
        System.out.println(openingText);
        System.out.println(horizontalLine);
    }
    /**
     * Returns void, just prints a message
     *
     * @param message message to be printed.
     */
    void printMessage(String message) {
        System.out.println(horizontalLine);
        System.out.println(message);
        System.out.println(horizontalLine);
    }

    /**
     * Returns void, just prints the closing tex
     */
    void printClosing() {

        System.out.println(horizontalLine);
        System.out.println(closingText);
        System.out.println(horizontalLine);
    }

    /**
     * Returns void, just prints the latest task added
     *
     * @param task the task that was added
     * @param len the length of the task list after adding the task
     */
    String printTaskAdded(Task task, int len) {
        String response = "";
        System.out.println(horizontalLine);
        response += horizontalLine + '\n';

        System.out.println("Got it. I've added this task:");
        response += "Got it. I've added this task:" + '\n';

        task.print();
        response += task.print() + '\n';

        System.out.println("Now you have " + len + " tasks in the list.");
        response += "Now you have " + len + " tasks in the list." + '\n';

        System.out.println(horizontalLine);
        response += horizontalLine + '\n';

        return response;
    }

    /**
     * Returns void, just prints the latest task deleted
     *
     * @param task the task that was added
     * @param len the length of the task list after deleting the task
     */
    String printTaskDeleted(Task task, int len) {
        String response = "";

        System.out.println(horizontalLine);
        response += horizontalLine + '\n';

        System.out.println("Noted. I've removed this task:");
        response += "Noted. I've removed this task:" + '\n';

        response += task.print() + '\n';

        System.out.println("Now you have " + len + " tasks in the list.");
        response += "Now you have " + len + " tasks in the list." + '\n';

        System.out.println(horizontalLine);
        response += horizontalLine + '\n';

        return response;
    }

    /**
     * Returns void, just prints the latest task marked
     *
     * @param task the task that was marked
     */
    String printTaskMarked(Task task) {
        String response = "";

        System.out.println(horizontalLine);
        response += horizontalLine + '\n';

        System.out.println("Nice! I've marked this task as done:");
        response += "Nice! I've marked this task as done:" + '\n';

        response += task.print() + '\n';

        System.out.println(horizontalLine);
        response += horizontalLine + '\n';

        return response;
    }

    /**
     * Returns void, just prints the latest task unmarked
     *
     * @param task the task that was unmarked
     */
    String printTaskUnmarked(Task task) {
        String response = "";

        System.out.println(horizontalLine);
        response += horizontalLine + '\n';

        System.out.println("OK, I've marked this task as not done yet:");
        response += "\"OK, I've marked this task as not done yet:" + '\n';

        response += task.print() + '\n';

        System.out.println(horizontalLine);
        response += horizontalLine + '\n';

        return response;
    }

    /**
     * Returns void, just prints error messages
     *
     * @param str the error message to print
     */
    void printError(String str) {
        System.out.println(horizontalLine);
        System.out.println(str);
        System.out.println(horizontalLine);
    }

    String openingText;
    String closingText;
    String horizontalLine;
}
