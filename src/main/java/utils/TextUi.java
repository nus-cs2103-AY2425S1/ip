package utils;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Representation of a text UI for ChatterBox.
 */
public class TextUi {
    private final Scanner in;
    private final PrintStream out;

    /**
     * Initialisation of a ChatterBox Ui
     */
    public TextUi() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    /**
     * Simple UI message for start of ChatterBox.
     */
    public void printWelcome() {
        out.print("""
                ____________________________________________________________
                Hello! I'm Chatterbox
                Below is your current list!
                What can I do for you?
                ____________________________________________________________
                """);
    }

    public void printTasks(StoredList taskList) {
        out.print(taskList.toString());
    }

    public void printMessage(String message) {
        out.println(message);
    }
}
