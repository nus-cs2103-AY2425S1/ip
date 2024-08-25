import java.util.ArrayList;
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

    // class properties
    private static ArrayList<String> userList;
    private static Scanner userInputScanner;
    // end of class properties

    // class methods
    private static void quoteSinglelineText(String line, StringBuilder sb) {
        sb.append("\n |  ");
        sb.append(line);
    }
    private static void quoteSinglelineText(String[] line, StringBuilder sb) {
        sb.append("\n |  ");
        for (String l : line) {
            sb.append(l);
        }
    }
    private static String quoteMultilineText(String text) {
        // annotates text with pipe to denote speech from bot
        String[] lines = text.split("\n");
        StringBuilder sb = new StringBuilder();
        for (String l : lines) {
            quoteSinglelineText(l, sb);
        }
        sb.append("\n"); // pad the end with another newline
        return sb.toString();
    }
    private static void printUserList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < userList.size(); i++) {
            // quoteSinglelineText(["1", "2"], sb);
        }
    }
    // end of class methods

    public static void main(String[] args) {
        // initialization
        userInputScanner = new Scanner(System.in);
        userList = new ArrayList<>();


        // start
        System.out.println(quoteMultilineText(GREETING_TEXT));

        programmeLoop:
        while (true) {
           String userInput = userInputScanner.nextLine();
           switch (userInput.toLowerCase()) {
               case "bye":
                   break programmeLoop;
               case "list":
                   printUserList();
               default:
                   userList.add(userInput);
                   System.out.println(quoteMultilineText(String.format("added: %s", userInput)));
                   break; // sanity break
           }
        }

        // exit
        System.out.println(quoteMultilineText(EXIT_TEXT));
    }
}
