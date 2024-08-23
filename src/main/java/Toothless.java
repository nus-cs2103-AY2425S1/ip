import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * Toothless represents a simple chat application.
 */
public class Toothless {

    private String toothlessLogo =
            " _____            _   _     _           \n" +
            "|_   _|___   ___ | |_| |__ | | ___  ___ ___ \n" +
            "  | |/ _ \\ / _ \\| __| '_ \\| |/ _ \\/ __/ __|\n" +
            "  | | (_) | (_) | |_| | | | |  __/\\__ \\__ \\\n" +
            "  |_|\\___/ \\___/ \\__|_| |_|_|\\___||___/___/\n";
    private String divider = "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n";
    private Scanner sc = new Scanner(System.in);

    /**
     * Constructor for Toothless.
     */
    private Toothless() {
        System.out.println("Welcome to the dragon's den!\n" + toothlessLogo);
    }

    /**
     * Prints a goodbye message.
     */
    public void printGoodbyeMessage() {
        System.out.println("Toothless: \n" +
                "Until next time, dragon rider! \n" +
                "Toothless the Night Fury, signing off. \n" +
                "See you soon! \n\n" + divider);
    }

    /**
     * Echoes the user's input.
     * @param input The user's input.
     */
    public void Echo(String input) {
        System.out.println("Toothless: \n" + input + "\n\n" + divider);
    }

    /**
     * Begins the chat application.
     * The user can type in messages and Toothless will echo them back.
     */
    public void beginChat() {
        System.out.println("Toothless: \n" +
                "Greetings, Dragon Rider! \n\n" +
                "I'm Toothless, your friendly dragon companion. \n" +
                "What adventure shall we embark on today? \n\n" + divider);

        while(true) {
            System.out.println("You : ");
            String userInput = sc.nextLine();
            System.out.println("\n" + divider);
            if (userInput.equals("bye")) {
                printGoodbyeMessage();
                break;
            }
            Echo(userInput);
        }

    }

    public static void main(String[] args) {
        Toothless toothless = new Toothless();

        toothless.beginChat();
    }
}