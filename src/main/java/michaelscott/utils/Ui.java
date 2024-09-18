package michaelscott.utils;

/**
 * This class represents user interface of MichaelScott.
 * Handles user input and output display.
 */
public class Ui {

    public static void showError(String message) {
        printLine();
        println(message);
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void println(String sentence) {
        System.out.println(sentence);
    }
}
