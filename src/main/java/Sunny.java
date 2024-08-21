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

    public static final String line = "     ────────────────────";
    public static final String welcome = "     HELLO! I am Sunny:)\n     How can I help you?";
    public static final String goodbye = "     You are leaving? Ok bye:( come back soon";


    public static void main(String[] args) {
        // WELCOME MESSAGE
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println(welcome);
        System.out.println(line);

        // USER INTERACTION
        while (true) {
            Scanner userInput = new Scanner(System.in);  // Create a Scanner object
            String userMessage = userInput.nextLine();  // Read user input

            if (Objects.equals(userMessage, "bye")) {
                break;
            } else if (Objects.equals(userMessage, "")) {
                System.out.println(line);
            } else {
                System.out.println(line);  // responds
                System.out.println("     " + userMessage);
                System.out.println(line);
            }
        }


        System.out.println(line);
        System.out.println(goodbye);
    }
}
