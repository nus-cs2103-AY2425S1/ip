package Nah.UI;

import java.util.Scanner;

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
    private static final String line = "________________________________________________________________________________\n";
    private static final String byeLine = " Bye. Hope to see you again soon!\n";

    private static final String unknown = " Nahhhhh!!! Please give me a valid command, such as list, mark, todo,...\n";

    private Scanner scanner = new Scanner(System.in);
    /**
     * Print out the greeting line.
     */
    public void greet() {
        System.out.println(greetLine);
    }

    /**
     * Read the next line in the input
     * @return
     */
    public String readCommand() {
        if (scanner.hasNext()) {
            return scanner.nextLine();
        }
        return "";
    }

    public void showLine() {
        System.out.println(line);
    }
    /**
     * Print out goodbye line.
     */
    public void exit() {

        System.out.println(byeLine);

    }

    /**
     * Print out the respond for the command
     * @param s
     */
    public void show(String s) {

        System.out.println(s);

    }
    public void unknownLine() {

        System.out.println(unknown);

    }
}

