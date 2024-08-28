package sora;

public class Ui {
    public static final String HORIZONTAL_LINE = "---------------------------------------------------";

    public void printGreeting() {
        System.out.println(HORIZONTAL_LINE + "\n" + "\tHello! I'm Sora!\n\tWhat can I do for you?\n" + HORIZONTAL_LINE);
    }

    public void printFarewell() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public void printInvalidCommand() {
        System.out.println("\tSora doesn't understand! Please Try Again!");
    }

    public void printUnableToWriteToFile() {
        System.out.println("\tSora is unable to write to file" + Ui.HORIZONTAL_LINE);
    }
}
