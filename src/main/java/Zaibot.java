import java.util.Scanner;
import java.util.ArrayList;

public class Zaibot {

    private static final ArrayList<String> tasks = new ArrayList<>();

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
        switch (command) {
            case "bye":
                printMessage(goodbyeMessage);
                break;
            case "list":
                printMessage(tasksListToString());
                break;
            default:
                tasks.add(command);
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
        for (String task : tasks) {
            current++;
            result.append(current).append(". ").append(task).append("\n");
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
