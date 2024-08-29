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

    public void showWelcomeMessage() {
        out.println(LOGO);
        out.println("Hello! My name is Victor!");
        out.println("What can I do for you?");
        out.println(DIVIDER);
    }

    public String getUserInput() {
        String userInput = in.nextLine();
        while (userInput.trim().isEmpty()) {
            userInput = in.nextLine();
        }
        out.println(DIVIDER);
        return userInput;
    }

    public void showUserMessage(ReturnMessage returnMessage) {
        if (!returnMessage.checkIsEmpty()) {
            for (String message : returnMessage.getMessages()) {
                out.println(message);
            }
            out.println(DIVIDER);
        }
    }

    public void showByeMessage() {
        out.println("  ~  Goodbye! Hope to see you again soon!");
        out.println(DIVIDER);
    }
}
