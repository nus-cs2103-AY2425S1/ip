import java.util.Scanner;
public class Duke {

    public static String horizontalLine = "----------------------------------------------------------";
    public static void printOpening() {
        String openingText = "Hello! I'm Jeff\n " +
                "What can I do for you?";

        System.out.println(horizontalLine);
        System.out.println(openingText);
        System.out.println(horizontalLine);
    }

    public static void printMessage(String message) {
        System.out.println(horizontalLine);
        System.out.println(message);
        System.out.println(horizontalLine);
    }

    public static void printClosing() {
        String closingText = "Bye. Hope to see you again soon!";

        System.out.println(horizontalLine);
        System.out.println(closingText);
        System.out.println(horizontalLine);
    }
    public static void main(String[] args) {

        String exitCommand = "bye";
        Scanner inputReader = new Scanner(System.in);

        printOpening();

        while(true)
        {
            String input = inputReader.nextLine();

            if(input.equals(exitCommand)) {
                printClosing();
                break;
            } else {
                printMessage(input);
            }
        }
    }
}
