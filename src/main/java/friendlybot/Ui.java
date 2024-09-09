package friendlybot;

/**
 * Ui deals with the interactions between FriendlyBot and the user.
 */
public class Ui {
    private static String exitMessage = "Bye. Hope to see you again soon!";

    /**
     * Prints the given String input with an indentation of 4 spaces.
     * @param str The String output that FriendlyBot wants to give the user.
     */
    public static void print(String str) {
        System.out.print("    ");
        System.out.println(str);
    }

    /**
     * Prints a welcome message for the user, when FriendlyBot is started.
     */
    public void showWelcome() {
        this.printHorizontalBar();
        Ui.print("Hello! I'm Friendly Bot");
        Ui.print("What can I do for you?");
        this.printHorizontalBar();
    }

    /**
     * Prints an exit message for the user, when the user wishes to exit FriendlyBot.
     */
    public String printExitMessage() {
        Ui.print(Ui.exitMessage);
        return Ui.exitMessage;
    }

    /**
     * Returns the exit message of Ui
     */
    public static String getExitMessage() {
        return Ui.exitMessage;
    }

    /**
     * Prints a horizontal bar for visual separation in the console output.
     */
    public void printHorizontalBar() {
        Ui.print("____________________________________________________________");
    }
}
