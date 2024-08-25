import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList;


public class Atlas {
    private static final String BOT_INDENT = "\t";
    private static final String SEP = BOT_INDENT + "___________________________________________";
    private static final String EXIT = "bye";
    private static final Scanner scanner = new Scanner(System.in);  // Create a Scanner object

    private static final ArrayList<String> list = new ArrayList<>(); 

    public static void main(String[] args) {
        introduction();
        String input = null;
        do {
            parse(input);
            input = listen();
        } while (!input.equals(EXIT));
        exit();
        scanner.close();
    }

    private static void separate() {
        System.out.println(SEP);
    }

    private static void parse(String input) {
        if (input == null) {
            return;
        }
        switch (input) {
            case "bye": // do nothing: handled in main loop
                break;
            case "list":
                listShow();
                break;
            default:  // add the item
                listAdd(input);
                listAddLog(input);
        }
    }

    private static void listAdd(String item) {
        list.add(item);
    }

    private static void listAddLog(String item) {
        System.out.println(BOT_INDENT + "added: " + item);
        separate();
    }

    private static void listShow() {
        System.out.println(BOT_INDENT + "Here are the items in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(BOT_INDENT + (i + 1) + ". " + list.get(i));
        }
        separate();
    }

    private static void introduction() {
        System.out.println(BOT_INDENT + "Hello! I'm Atlas.");
        System.out.println(BOT_INDENT + "How can I help you today?");
        separate();
    }

    private static void exit() {
        System.out.println(BOT_INDENT + "Goodbye! Have a great day ahead!");
        separate();
    }

    private static String listen() {
        System.out.println("");
        String text = scanner.nextLine();  // Read user input
        separate();
        return text;
    }

    private static void echo(String input) {
        if (input == null) {
            return;
        }
        System.out.println(BOT_INDENT + input);
        separate();
    }
}
