package nah.ui;

import java.util.Scanner;

/**
 * Handles the interaction with user by print out correct response.
 */
public class UI {
    private static String logo = "| \\   | |      /\\      | |  | |\n"
            + "| |\\  | |     / /\\     | |==| |\n"
            + "| | \\ | |    / /__\\    | |  | |\n"
            + "| |  \\  |   / /    \\   | |  | |\n";
    private static final String greetLine = "Hello from\n"
            + logo
            + "________________________________________________________________________________\n"
            + " Hello! I'm NAH\n"
            + " What can I do for you?\n"
            + "________________________________________________________________________________\n";
    private static final String line =
            "________________________________________________________________________________\n";
    private static final String byeLine = " Bye. Hope to see you again soon!\n";

    private static final String unknown =
            " Nahhhhh!!! Please give me a valid command, such as list, mark, todo,...\n";

    private Scanner scanner = new Scanner(System.in);

    /**
     * Prints out the greeting line.
     */
    public void greet() {
        System.out.println(greetLine);
    }

    /**
     * Reads the next line in the input.
     *
     * @return a String of that next line
     */
    public String readCommand() {
        if (scanner.hasNext()) {
            return scanner.nextLine();
        }
        return "";
    }

    /**
     * Prints out a default line
     */
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Prints out goodbye line.
     */
    public void exit() {

        System.out.println(byeLine);

    }

    /**
     * Prints out the response for the command.
     * This response was delivered from execute method in nah.command.Command
     *
     * @param s the response
     */
    public void show(String s) {

        System.out.println(s);

    }

    /**
     * Prints out the unknown line.
     */
    public void unknownLine() {

        System.out.println(unknown);

    }
}

