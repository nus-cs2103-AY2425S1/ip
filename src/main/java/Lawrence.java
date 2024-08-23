import java.util.ArrayList;
import java.util.Scanner;

public class Lawrence {
    private static final String NAME = "Lawrence";
    private static final ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        greetUser();
        Scanner sc = new Scanner(System.in);

        String userInput;
        while (true) {
            userInput = sc.nextLine();  // Get next user input
            switch (userInput) {
                case "bye":
                    printMessage("That's all folks! Hope to see you again soon!");
                    return;
                case "list":
                    printList();
                    break;
                default:
                    list.add(userInput);
                    printMessage(String.format("Added '%s' to the list.", userInput));
            }
        }
    }   

    private static void greetUser() {
        String greeting = String.format("Hello! I'm %s and I'm here to steal your credit card information.\n"
                + "What can I do for you?", NAME);
        printMessage(greeting);
    }

    private static void printList() {
        StringBuilder result = new StringBuilder();
        int serialNumber = 1;
        for (String item : list) {
            result.append(String.format("%d. %s%n", serialNumber, item));
            serialNumber++;
        }

        // exclude the last newline character from getting printed
        printMessage(result.substring(0, result.length() - 1));
    }

    private static void printMessage(String message) {
        printHorizontalLine();
        System.out.println(message);
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println("____________________");
    }
}
