import java.util.Scanner;
import java.util.ArrayList;

public class Zaibot {

    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Prints a message, with the horizontal line above and below it
     * @param message   The message to be printed
     */
    public static void printMessage(String message) {
        String line = "---------------------------------------\n";
        System.out.println(line + message + line);
    }

    /**
     * Processes a command, based on the input.
     * @param command The command input
     */
    public static void processCommand(String command) {
        String goodbyeMessage = "Bye. Hope to see you again soon!\n";
        String markTaskMessage = "Nice! I've marked this task as done:\n";
        String unmarkTaskMessage = "OK, I've marked this task as not done yet:\n";

        String[] options = command.split("\\s+");

        Task current;

        switch (options[0]) {
            case "bye":
                printMessage(goodbyeMessage);
                break;
            case "list":
                printMessage(tasksListToString());
                break;
            case "mark":
                current = tasks.get(Integer.parseInt(options[1]) - 1);
                current.setCompletionStatus(true);
                printMessage(markTaskMessage + current.toString() + "\n");
                break;
            case "unmark":
                current = tasks.get(Integer.parseInt(options[1]) - 1);
                current.setCompletionStatus(false);
                printMessage(unmarkTaskMessage + current.toString() + "\nr");
                break;
            default:
                tasks.add(new Task(command));
                printMessage("added: " + command + "\n");
        }
    }

    /**
     * Takes in the list of tasks, and converts it into a string.
     * @return A string of all the tasks, enumerated.
     */
    public static String tasksListToString() {
        StringBuilder result = new StringBuilder();
        int current = 0;
        for (Task task : tasks) {
            current++;
            result.append(current).append(". ").append(task.toString()).append("\n");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String greetingMessage = "Hello! I'm zAIbot.\nWhat can I do for you?\n";
        printMessage(greetingMessage);

        String currentCommand = "";

        while (!currentCommand.equals("bye")) {
            Scanner input = new Scanner(System.in);
            currentCommand = input.nextLine();
            processCommand(currentCommand);
        }

    }
}
