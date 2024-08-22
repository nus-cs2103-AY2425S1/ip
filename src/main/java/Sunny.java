import java.util.Objects;
import java.util.Scanner;

public class Sunny {

    // Chatbot messages
    public static final String logo = "       _____   _    _  _   _  _   _  __     __  \n" +
            "      / ____| | |  | || \\ | || \\ | | \\ \\   / /  \n" +
            "     | (___   | |  | ||  \\| ||  \\| |  \\ \\_/ /   \n" +
            "      \\___ \\  | |  | || . ` || . ` |   \\   /    \n" +
            "      ____) | | |__| || |\\  || |\\  |    | |     \n" +
            "     |_____/   \\____/ |_| \\_||_| \\_|    |_|     \n";

    private static Replies toUser = new Replies();

    public static void main(String[] args) {
        // WELCOME MESSAGE
        System.out.println("Hello from\n" + logo);
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
