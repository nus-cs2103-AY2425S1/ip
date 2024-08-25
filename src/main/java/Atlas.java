import java.util.Scanner;  // Import the Scanner class

public class Atlas {
    private static final String BOT_INDENT = "\t";
    private static final String SEP = BOT_INDENT + "___________________________________________";
    private static final String EXIT = "bye";
    private static Scanner scanner = new Scanner(System.in);  // Create a Scanner object

    public static void main(String[] args) {
        introduction();
        String input = null;
        do {
            echo(input);
            input = listen();
        } while (!input.equals(EXIT));
        exit();
        scanner.close();
    }

    private static final void separate() {
        System.out.println(SEP);
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
