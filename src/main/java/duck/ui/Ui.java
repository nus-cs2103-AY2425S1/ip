package duck.ui;

import duck.data.exception.DuckException;

import java.util.Scanner;

/**
 * Handles user interactions with the Duck application, including displaying messages
 * and reading user input from the console.
 */
public class Ui {
    private Scanner in;

    /**
     * Constructs a Ui object with a new Scanner for reading user input.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Displays the startup message indicating that Duck is being initialized.
     */
    public void showStartUpMessage() {
        System.out.println("Initializing Duck...");
    }

    /**
     * Displays a message indicating that Duck has started up successfully.
     */
    public void showStartUpCompleteMessage() {
        System.out.println("Quack. Duck is up!");
    }

    /**
     * Displays the greeting message along with an ASCII logo and a prompt for user interaction.
     */
    public void sayHi() {
        String logo = """
                        ,---,                                  ,-.
                      .'  .' `\\                            ,--/ /|
                    ,---.'     \\          ,--,           ,--. :/ |
                    |   |  .`\\  |       ,'_ /|           :  : ' /
                    :   : |  '  |  .--. |  | :    ,---.  |  '  /
                    |   ' '  ;  :,'_ /| :  . |   /     \\ '  |  :
                    '   | ;  .  ||  ' | |  . .  /    / ' |  |   \\
                    |   | :  |  '|  | ' |  | | .    ' /  '  : |. \\
                    '   : | /  ; :  | : ;  ; | '   ; :__ |  | ' \\ \\
                    |   | '` ,/  '  :  `--'   \\'   | '.'|'  : |--'
                    ;   :  .'    :  ,      .-./|   :    :;  |,'
                    |   ,.'       `--`----'     \\   \\  / '--'
                    '---'                        `----'
                """;

        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duck.");
        System.out.println("What can I do for you, QUACK?\n");
    }

    /**
     * Reads a line of user input from the console.
     *
     * @return The user input as a trimmed string. Returns "bye" if no input is available.
     */
    public String readCommand() {
        if (in.hasNextLine()) {
            return(in.nextLine().trim());
        }
        return "bye";
    }

    /**
     * Displays a message indicating that Duck is shutting down.
     */
    public void showGoodbyeMessage() {
        System.out.println("Quack! Duck is going to sleep now. Goodbye!");
    }

    /**
     * Displays the message from a DuckException.
     *
     * @param de The DuckException whose message will be displayed.
     */
    public void displayDukeExceptionMessage(DuckException de) {
        System.out.println(de.getMessage());
    }

    /**
     * Displays a message indicating that the user provided an invalid instruction.
     *
     * @param iae The IllegalArgumentException associated with the invalid instruction.
     */
    public void displayIllegalArgumentMessage(IllegalArgumentException iae) {
        System.out.println("Quack, that's not a valid instruction! I don't know how to respond to that.");
    }
}
