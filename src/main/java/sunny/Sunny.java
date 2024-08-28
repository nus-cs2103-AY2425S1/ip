package sunny;
import java.util.Objects;
import java.util.Scanner;

/**
 * Contains the main loop of the program, initialise java scanner objects and Ui objects
 */
public class Sunny {
    private static Ui toUser = new Ui();


    /**
     * Executes main loop of the program
     * @param args original input
     */
    public static void main(String[] args) {
        // WELCOME MESSAGE
        System.out.println(toUser.welcome());

        // USER INTERACTION
        while (true) {
            Scanner userInput = new Scanner(System.in);  // Create a Scanner object
            String userMessage = userInput.nextLine();  // Read user input

            if (Objects.equals(userMessage, "bye")) {
                System.out.println(toUser.reply("bye"));
                break;
            } else {
                System.out.println(toUser.reply(userMessage));
            }


        }
    }
}
