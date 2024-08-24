import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the Bob Chatbot application.
 */
public class Bob {
    public static void main(String[] args) {
        try {
            ChatBot bot = new ChatBot("./data/bob.txt");
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
        } catch (IOException e) {
            System.out.println("Oh no! Something wrong occurred while reading or saving your tasks.");
        }
    }
}
