import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChadGPT {
    private static final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printBotMessage("Hello! I'm ChadGPT. What can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            handleInput(sc.nextLine());
        }
    }

    private static void handleInput(String input) {
        Pattern regex = Pattern.compile("(\\w+)\\s*(.*)");
        Matcher matcher = regex.matcher(input);
        if (matcher.matches()) {
            String command = matcher.group(1);
            String args = matcher.group(2);

            switch (command) {
                case "list":
                    handleList();
                    break;
                case "todo":
                    handleAddTask(args);
                    break;
                case "mark":
                    handleMarkTask(args);
                    break;
                case "unmark":
                    handleUnmarkTask(args);
                    break;
                case "bye":
                    printBotMessage("Bye. Hope to see you again soon!");
                    System.exit(0);
                default:
                    // TODO? Possibly find a better way to handle this
                    printBotMessage("Command not found");
            }
        } else {
            // TODO: Throw exception
        }
    }

    private static void handleList() {
        printBotMessage("Here are the tasks in your list:\n" + Formatter.formatList(tasks));
    }
    private static void handleMarkTask(String args) {
        int index = getTaskIndex(args);
        tasks.get(index).markAsDone();
        printBotMessage("Nice! I've marked this task as done:\n" + tasks.get(index));
    }

    private static void handleUnmarkTask(String args) {
        int index = getTaskIndex(args);
        tasks.get(index).markAsIncomplete();
        printBotMessage("OK, I've marked this task as not done yet:\n" + tasks.get(index));
    }

    private static int getTaskIndex(String input) {
        return Integer.parseInt(input.split(" ")[0]) - 1;
    }

    private static void handleAddTask(String args) {
        tasks.add(new Task(args));
        printBotMessage("added: " + args);
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
