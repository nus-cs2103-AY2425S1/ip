package duck.ui;



import duck.data.exception.DuckException;

import java.util.Scanner;




/**
 * Handles user interactions with the Duck application, including displaying messages
 * and reading user input from the console.
 */
public class Ui {
    public static final String MESSAGE_START_UP = "Initializing Duck...";
    public static final String MESSAGE_START_UP_COMPLETE = """
            Quack. Duck is up!
            If you need Duck's guidance, type 'help'!
            """;
    public static final String MESSAGE_LOGO = """
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
    public static final String MESSAGE_GOODBYE = "Quack! Duck is going to sleep now. Goodbye!";
    public static final String WORD_BYE = "bye";
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
        System.out.println(MESSAGE_START_UP);
    }

    /**
     * Displays a message indicating that Duck has started up successfully.
     */
    public void showStartUpCompleteMessage() {
        System.out.println(MESSAGE_START_UP_COMPLETE);
    }

    /**
     * Displays the greeting message along with an ASCII logo and a prompt for user interaction.
     */
    public void sayHi() {
        System.out.println("Hello from\n" + MESSAGE_LOGO);
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
            return in.nextLine().trim();
        }
        return WORD_BYE;
    }

    /**
     * Displays a message indicating that Duck is shutting down.
     */
    public void showGoodbyeMessage() {
        System.out.println(MESSAGE_GOODBYE);
    }

    /**
     * Displays the message from a DuckException.
     *
     * @param de The DuckException whose message will be displayed.
     */
    public void displayDukeExceptionMessage(DuckException de) {
        System.out.println(de.getMessage());
    }


    public void showHelpMessage(String helpString) {
        System.out.println(helpString);
    }
}
