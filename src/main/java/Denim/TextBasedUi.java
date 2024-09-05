package denim;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import denim.commands.CommandResult;

/**
 * The primary Ui for the application. It is responsible for receiving user input and passes it to the Parser.
 * It is also involved in returning feedback to the user after a command has been executed.
 */
public class TextBasedUi {

    public static final int INDEX_OFFSET = 1;
    static final String HORIZONTAL_LINE = "____________________________________________________________";
    static final String CHAT_BOT_NAME = "denim";

    private final Scanner in;
    private final PrintStream out;

    public TextBasedUi() {
        this(System.in, System.out);
    }

    public TextBasedUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns a String of the user's input.
     *
     * @return input Which is the user input.
     */
    public String getUserCommand() {
        String input = in.nextLine();
        return input;
    }


    /**
     * Prints a message for the user after the command has been executed.
     *
     * @param result The CommandResult containing the reply messages.
     */
    public void displayReplyMessage(CommandResult result) {
        String reply = result.getReply();
        System.out.printf("%s%n%s%n%s%n", HORIZONTAL_LINE, reply, HORIZONTAL_LINE);
    }

    /**
     * Prints a greeting message for the user after the application successfully loads.
     *
     */
    public void displayGreetingMessage() {
        String greetingMessage = String.format("%s%n Hello! I'm %s!%n What can I do for you? %n%s%n",
                HORIZONTAL_LINE, CHAT_BOT_NAME, HORIZONTAL_LINE);
        System.out.println(greetingMessage);
    }

    /**
     * Prints an error message for the user after the application is unable to find the required directory.
     *
     */
    public String displayDirectoryNotFoundMessage() {
        System.out.println("data directory and corresponding denim.txt not found. Create both? (y / n)\n");
        String input = in.nextLine();
        return input;
    }

    /**
     * Prints an error message for the user after the application is unable to find the required file.
     *
     */
    public String displayFileNotFoundMessage() {
        System.out.println("denim.txt not found in data directory. Create denim.txt? (y / n)\n");
        String input = in.nextLine();
        return input;
    }
}
