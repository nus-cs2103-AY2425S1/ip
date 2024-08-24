import java.util.Scanner;

/**
 * Represents the Bob Chatbot application.
 */
public class Bob {
    public static void main(String[] args) {
        ChatBot bot = new ChatBot();
        System.out.print(bot.greet());

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String input = in.nextLine();
            String output = bot.input(input);
            System.out.print(output);

            if (input.equals("bye")) {
                break;
            }
        }
    }
}
