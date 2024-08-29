package denim;

import denim.commands.CommandResult;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    static final String horizontalLine = "____________________________________________________________";
    static final String chatBotName = "denim";

    public static final int indexOffset = 1;

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserCommand() {
        String input = in.nextLine();
        return input;
    }

    public void displayReplyMessage(CommandResult result) {
        String reply = result.getReply();
        System.out.printf("%s%n%s%n%s%n", horizontalLine, reply, horizontalLine);
    }

    public void displayGreetingMessage() {
        String greetingMessage = String.format("%s%n Hello! I'm %s!%n What can I do for you? %n%s%n",
                horizontalLine, chatBotName, horizontalLine);
        System.out.println(greetingMessage);
    }

    public String displayDirectoryNotFoundMessage() {
        System.out.println("data directory and corresponding denim.txt not found. Create both? (y / n)\n");
        String input = in.nextLine();
        return input;
    }

    public String displayFileNotFoundMessage() {
        System.out.println("denim.txt not found in data directory. Create denim.txt? (y / n)\n");
        String input = in.nextLine();
        return input;
    }
}
