package bot;

import bot.utils.Formatter;

public class Ui {
    /**
     * Prints a welcome message.
     */
    public void start() {
        printMessage("Hello! I'm ChadGPT. What can I do for you?");
    }

    /**
     * Prints a goodbye message.
     */
    public void exit() {
        printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a formatted bot message
     *
     * @param msg the string message to be printed
     */
    public void printMessage(String msg) {
        System.out.println(Formatter.formatBotMessage(msg));
    }
}
