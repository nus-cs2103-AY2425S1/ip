import java.io.PrintStream;
import java.util.Scanner;

public class UI {
    private static final String DIVIDER = "============================================================";
    private static final String LOGO =  ",---.  ,---..-./`)     _______ ,---------.    ,-----.    .-------.\n"
            + "|   /  |   |\\ .-.')   /   __  \\\\          \\ .'  .-,  '.  |  _ _   \\    \n"
            + "|  |   |  .'/ `-' \\  | ,_/  \\__)`--.  ,---'/ ,-.|  \\ _ \\ | ( ' )  |\n"
            + "|  | _ |  |  `-'`\"`,-./  )         |   \\  ;  \\  '_ /  | :|(_ o _) /\n"
            + "|  _( )_  |  .---. \\  '_ '`)       :_ _:  |  _`,/ \\ _/  || (_,_).' __\n"
            + "\\ (_ o._) /  |   |  > (_)  )  __   (_I_)  : (  '\\_/ \\   ;|  |\\ \\  |  |\n"
            + " \\ (_,_) /   |   | (  .  .-'_/  ) (_(=)_)  \\ `\"/  \\  ) / |  | \\ `'   /\n"
            + "  \\     /    |   |  `-'`-'     /   (_I_)    '. \\_/``\".'  |  |  \\    /\n"
            + "   `---`     '---'    `._____.'    '---'      '-----'    ''-'   `'-'\n";
    private final Scanner in;
    private final PrintStream out;

    public UI() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public void welcomeMessage() {
        out.println(LOGO);
        out.println("Hello! My name is Victor!");
        out.println("What can I do for you?");
        out.println(DIVIDER);
    }

    public String getUserInput() {
        out.println(DIVIDER);
        String userInput = in.nextLine();
        while (userInput.trim().isEmpty()) {
            userInput = in.nextLine();
        }
        return userInput;
    }

    public void showUserMessage(String... messages) {
        for (String message : messages) {
            out.println(message);
        }
        out.println(DIVIDER);
    }
}
