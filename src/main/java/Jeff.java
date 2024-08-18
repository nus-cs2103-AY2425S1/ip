import java.util.Scanner;
import java.util.ArrayList;

public class Jeff {
    private static final String HORIZONTAL = "____________________________________________";
    private static ArrayList<String> inputList = new ArrayList<>();

    // Function for enclosing text with horizontal lines and printing it out
    private static void printText(String text) {
        System.out.println(indentText(HORIZONTAL + "\n " + text + "\n" + HORIZONTAL));
    }

    // Function to indent text
    private static String indentText(String text) {
        // Split the text into lines
        String[] lines = text.split("\n");

        // Initialise a StringBuilder
        StringBuilder indentedText = new StringBuilder();

        // Add indentation to each line
        for (String line : lines) {
            indentedText.append("\t").append(line).append("\n");
        }

        // Convert the StringBuilder back to a String
        return indentedText.toString();
    }

    // Function for printing out greetings
    private static void printGreetings() {
        printText("Hello! I'm Jeff.\n What can I do for you?");
    }

    // Function for printing out farewells
    private static void printFarewell() {
        printText("Bye. Hope to see you again soon!");
    }

    // Function for printing out the input list
    private static void printList() {
        // Check if the list is empty
        if (inputList.isEmpty()) {
            printText("List is empty!");
            return;
        }

        // Initialise a StringBuilder
        StringBuilder listString = new StringBuilder();

        // Loop through the inputList and add it to the StringBuilder
        for (int i = 0; i < inputList.size(); i++) {
            listString.append(Integer.toString(i + 1)).append(". ").append(inputList.get(i));

            // Only add a new line when it is not the last string in the list
            if (i != inputList.size() - 1) {
                listString.append("\n ");
            }
        }

        // Print the text
        printText(listString.toString());
    }

    // Function for printing out user input
    private static void printUserInput() {
        // Initialise the Scanner
        Scanner scanner = new Scanner(System.in);
        String input = "";

        // Continue looping until input is bye
        while (!"bye".equals(input)) {
            // Prompt the user for input
            System.out.print("");

            // Get the input
            input = scanner.nextLine();

            // Check for input == some keyword
            switch (input) {
                case "list":
                    printList();
                    break;
                case "bye":
                    break;
                default:
                    printText("added: " + input);
                    inputList.add(input);
                    break;
            }
        }

        // Close the Scanner
        scanner.close();
    }

    public static void main(String[] args) {
        printGreetings();
        printUserInput();
        printFarewell();
    }
}
