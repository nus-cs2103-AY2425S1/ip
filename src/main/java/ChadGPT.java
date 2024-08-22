import java.util.Scanner;

public class ChadGPT {
    public static void main(String[] args) {
        printBotMessage("Hello! I'm ChadGPT. What can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            // TODO: Use regex for more advanced matching
            if (input.startsWith("bye")) {
                printBotMessage("Bye. Hope to see you again soon!");
                break;
            } else {
                printBotMessage(input);
            }
        }
    }

    /**
     * Prints a formatted bot message
     *
     * @param msg the string message to be printed
     */
    public static void printBotMessage(String msg) {
        System.out.println(Formatter.formatBotMessage(msg));
    }
}
