import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    public static String horizontalLine = "----------------------------------------------------------";

    public static ArrayList<String> addedMessages = new ArrayList<String>();
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

    public static void addMessage(String message) {
        printMessage("added: " + message);
        addedMessages.add(message);
    }

    public static void listMessages()
    {
        int numberOfMessages = addedMessages.size();

        System.out.println(horizontalLine);
        for(int i=0; i<numberOfMessages; i++) {
            System.out.println((i+1) + ". " + addedMessages.get(i));
        }
        System.out.println(horizontalLine);
    }
    public static void main(String[] args) {

        String exitCommand = "bye";
        String listCommand = "list";

        Scanner inputReader = new Scanner(System.in);

        printOpening();

        while(true)
        {
            String input = inputReader.nextLine();

            if(input.equals(exitCommand)) {
                printClosing();
                break;
            } else if(input.equals("list")) {
                listMessages();
            } else {
                addMessage(input);
            }
        }
    }
}
