package victor.ui;

import java.io.PrintStream;
import java.util.Scanner;

import victor.messages.ReturnMessage;

public class UI {
    private static final String DIVIDER = "============================================================";
    private static final String LOGO = """
            ,---.  ,---..-./`)     _______ ,---------.    ,-----.    .-------.
            |   /  |   |\\ .-.')   /   __  \\\\          \\ .'  .-,  '.  |  _ _   \\   \s
            |  |   |  .'/ `-' \\  | ,_/  \\__)`--.  ,---'/ ,-.|  \\ _ \\ | ( ' )  |
            |  | _ |  |  `-'`"`,-./  )         |   \\  ;  \\  '_ /  | :|(_ o _) /
            |  _( )_  |  .---. \\  '_ '`)       :_ _:  |  _`,/ \\ _/  || (_,_).' __
            \\ (_ o._) /  |   |  > (_)  )  __   (_I_)  : (  '\\_/ \\   ;|  |\\ \\  |  |
             \\ (_,_) /   |   | (  .  .-'_/  ) (_(=)_)  \\ `"/  \\  ) / |  | \\ `'   /
              \\     /    |   |  `-'`-'     /   (_I_)    '. \\_/``".'  |  |  \\    /
               `---`     '---'    `._____.'    '---'      '-----'    ''-'   `'-'
            """;
    private final Scanner in;
    private final PrintStream out;

    public UI() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    /**
     * Displays the program welcome message to the user in the Standard Output.
     */
    public void welcomeMessage() {
        out.println(LOGO);
        out.println("Hello! My name is Victor!");
        out.println("What can I do for you?");
        out.println(DIVIDER);
    }

    /**
     * Reads and returns the user input from the Standard Input.
     * @return A string of the user input.
     */
    public String getUserInput() {
        String userInput = in.nextLine();
        while (userInput.trim().isEmpty()) {
            userInput = in.nextLine();
        }
        out.println(DIVIDER);
        return userInput;
    }

    /**
     * Displays the contents of the return message to the user in the Standard Output.
     * @param returnMessage A ReturnMessage item containing details of the lines to
     *                      be printed and shown to the user.
     */
    public void showUserMessage(ReturnMessage returnMessage) {
        if (!returnMessage.isEmpty()) {
            for (String message : returnMessage.getMessages()) {
                out.println(message);
            }
            out.println(DIVIDER);
        }
    }

    /**
     * Displays the parting message of the program to the user.
     */
    public void byeMessage() {
        out.println("  ~  Goodbye! Hope to see you again soon!");
        out.println(DIVIDER);
    }
}
