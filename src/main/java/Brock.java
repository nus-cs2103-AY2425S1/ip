import java.util.Scanner;

public class Brock {
    private static final String horizontalLine = "________________________________________________\n";
    private static final String[] items = new String[100];
    private static int itemsIndex = 0;

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
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                String byeMessage = "Bye. Hope to see you again soon!\n";
                displayResponse(byeMessage);
                break;
            } else if (command.equals("list")) {
                // Use SB as it is a faster way to append strings
                StringBuilder itemsString = new StringBuilder();
                for (int i = 0; i < 100; i++) {
                    if (items[i] == null) {
                        break;
                    }
                    String itemNumber = Integer.toString(i + 1);
                    itemsString.append(itemNumber).append(". ").append(items[i]);
                }
                displayResponse(itemsString.toString());
            } else {
                displayResponse("added: " + command + '\n');
                Brock.items[itemsIndex] = command + '\n';
                itemsIndex++;
            }
        }
    }
}

