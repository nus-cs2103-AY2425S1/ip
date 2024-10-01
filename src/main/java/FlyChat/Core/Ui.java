package flychat.core;

/**
 * Contains methods dealing with user interaction, such as scanning for user input and
 * managing output to System.out.
 */
public class Ui {
    private static final String lineBreak = "--------------------\n";


    /**
     * Outputs the application start-up text to the user.
     */
    public static String greetUser() {
        return lineBreak + "Hello! I'm FlyChat\nWhat can I do for you?\n" + lineBreak;
    }

    /**
     * Outputs the application closing text to the user.
     */
    public String bye() {
        return lineBreak + "Bye. Hope to see you again soon!\n" + lineBreak;
    }

    /**
     * Formats and announces actions taken by the application to the user.
     *
     * @param str String to be announced.
     */
    public String announceString(String str) {
        return lineBreak + str + "\n" + lineBreak;
    }
}
