import java.util.Scanner;

public class Jeff {
    // String for horizontal line
    private static final String HORIZONTAL = "____________________________________________";

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

            // Print the input
            if (!input.equals("bye")) {
                printText(input);
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
