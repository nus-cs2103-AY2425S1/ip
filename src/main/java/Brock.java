import java.util.Scanner;

public class Brock {
    private static final String horizontalLine = "________________________________________________\n";

    // Helper function to display response with lines
    private static void displayResponse(String response) {
        System.out.println(Brock.horizontalLine + response + Brock.horizontalLine);
    }
    public static void main(String[] args) {
        // Create a scanner object
        // Reads from standard system input
        Scanner scanner = new Scanner(System.in);

        // Initial message
        String initialPart1 = "Hello! I'm Brock\n";
        String initialPart2 = "What can I do for you?\n";
        String initial = initialPart1 + initialPart2;
        displayResponse(initial);

        // Main loop
        while (true) {
            String command = scanner.nextLine() + '\n';
            if (command.equals("bye\n")) {
                String byeMessage = "Bye. Hope to see you again soon!\n";
                displayResponse(byeMessage);
                break;
            } else {
                displayResponse(command);
            }
        }
    }
}

