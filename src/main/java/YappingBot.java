import java.util.ArrayList;
import java.util.Scanner;

public class YappingBot {
    // Text strings
    private static final String BOT_NAME = "YappingBot";
    private static final String GREETING_TEXT = String.format(
            "Hello! I'm %s\nWhat can I do for you?",
            BOT_NAME
    );
    private static final String LIST_TEXT = "Here are the tasks in your list:";
    private static final String EXIT_TEXT = "Bye. Hope to see you again soon!";
    // End of text strings

    // class properties
    private static ArrayList<Task> userList;
    private static Scanner userInputScanner;
    // end of class properties

    // class methods
    private static String quoteSinglelineText(String line) {
       return String.format("\n |  %s", line);
    }
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
        if (userList.isEmpty()) {
            System.out.println(quoteSinglelineText("List is empty!\n"));
            return;
        }

        StringBuilder sb = new StringBuilder();
        quoteSinglelineText(LIST_TEXT, sb);
        for (int i = 0; i < userList.size(); i++) {
            Task t = userList.get(i);
            quoteSinglelineText(
                    String.format("%2d.[%s] %s", i+1, t.getTaskDoneCheckmark(), t.getTaskName()),
                    sb
            );
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }
    // end of class methods

    public static void main(String[] args) {
        // initialization
        userInputScanner = new Scanner(System.in);
        userList = new ArrayList<>();

        // start
        System.out.println(quoteMultilineText(GREETING_TEXT));

        programmeLoop: // to break out of loop
        while (true) {
           String userInput = userInputScanner.nextLine();
           switch (userInput.toLowerCase()) {
               case "bye":
                   break programmeLoop;
               case "":
                   break; // ignore multiple enters
               case "list":
                   printUserList();
                   break;
               case "mark":

               default:
                   userList.add(new Task(userInput, false));
                   System.out.println(quoteMultilineText(String.format("added: %s", userInput)));
                   break; // sanity break
           }
        }

        // exit
        System.out.println(quoteMultilineText(EXIT_TEXT));
    }
}
