import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChadGPT {
    private static final List<Task> tasks = new ArrayList<>();

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
                printBotMessage("Here are the tasks in your list:\n" + Formatter.formatList(tasks));
            } else if (input.startsWith("mark")) {
                int i = getTaskIndex(input);
                markTask(i);
                printBotMessage("Nice! I've marked this task as done:\n" + tasks.get(i));
            } else if (input.startsWith("unmark")) {
                int i = getTaskIndex(input);
                unmarkTask(i);
                printBotMessage("OK, I've marked this task as not done yet:\n" + tasks.get(i));
            } else {
                addTaskToList(input);
                printBotMessage("added: " + input);
            }
        }
    }

    private static void markTask(int index) {
        tasks.get(index).markAsDone();
    }

    private static void unmarkTask(int index) {
        tasks.get(index).markAsIncomplete();
    }

    private static int getTaskIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    private static void addTaskToList(String desc) {
        tasks.add(new Task(desc));
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
