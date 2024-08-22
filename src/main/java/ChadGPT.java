import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChadGPT {
    private static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printBotMessage("Hello! I'm ChadGPT. What can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            // TODO: Use regex for more advanced matching
            if (input.startsWith("bye")) {
                printBotMessage("Bye. Hope to see you again soon!");
                break;
            } else if (input.startsWith("list")) {
                printBotMessage(Formatter.formatList(tasks));
            } else {
                addTaskToList(input);
                printBotMessage("added: " + input);
            }
        }
    }

    private static void addTaskToList(String task) {
        tasks.add(task);
    }

    /**
     * Prints a formatted bot message
     *
     * @param msg the string message to be printed
     */
    private static void printBotMessage(String msg) {
        System.out.println(Formatter.formatBotMessage(msg));
    }
}
