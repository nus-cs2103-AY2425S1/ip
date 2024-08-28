package optimus;

import java.util.Scanner;

public class Ui {

    Scanner scanner;
    private static final String linebreak = "____________________________";
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads user input from the keyboard
     * @return - user input
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a greeting. Should be used only upon booting up Optimus.
     */
    public void greet() {
        System.out.println("Hello! I'm optimus.Optimus\nWhat can I do for you?");
        showLineBreak();
    }

    /**
     * Prints the linebreak to the UI
     */
    public void showLineBreak() {
        System.out.println(linebreak);
    }

    /**
     * Prints the argument to the UI for users to see
     * @param out - string to be printed
     */
    public void printToInterface(String out) {
        System.out.println(out);
    }


}
