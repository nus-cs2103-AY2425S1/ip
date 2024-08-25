import java.util.Scanner;

public class YappingBot {
    // Text strings
    private static final String BOT_NAME = "YappingBot";
    private static final String GREETING_TEXT = String.format(
            "Hello! I'm %s\nWhat can I do for you?",
            BOT_NAME
    );
    private static final String EXIT_TEXT = "Bye. Hope to see you again soon!";
    // End of text strings

    private static String quoteText(String text) {
        // annotates text with pipe to denote speech from bot
        String[] lines = text.split("\n");
        StringBuilder sb = new StringBuilder();
        for (String l : lines) {
            sb.append("\n |  ");
            sb.append(l);
        }
        sb.append("\n"); // pad the end with another newline
        return sb.toString();
    }

    public static void main(String[] args) {
        // scanner for user input
        Scanner userInputScanner = new Scanner(System.in);
        // start
        System.out.println(quoteText(GREETING_TEXT));

        programmeLoop:
        while (true) {
           String userInput = userInputScanner.nextLine();
           switch (userInput.toLowerCase()) {
               case "bye":
                   break programmeLoop;
               default:
                   System.out.println(quoteText(String.format("added: %s", userInput)));
                   break; // sanity break
           }
        }

        // exit
        System.out.println(quoteText(EXIT_TEXT));
    }
}
