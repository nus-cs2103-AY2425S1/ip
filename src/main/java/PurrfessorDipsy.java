import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PurrfessorDipsy {
    private static final Task[] taskTable = new Task[100];
    private static int taskTableRowCount = 0;

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        printWelcomeMessage();
        while (true) {
            String userInput = inputScanner.nextLine();
            doAction(userInput);
        }
    }

    private static void printTerminalLine() {
        String TERMINAL_LINE = "―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――";
        System.out.println(TERMINAL_LINE);
    }

    private static void printWelcomeMessage() {
        printTerminalLine();
        System.out.println("Meowdy! I'm Purrfessor Dipsy, Keeper of the Cozy Sunbeam " +
                "and Purrtector of the Realm of Naps.\nHow can I purrvide assistance? " +
                "Purrhaps I can lend a paw!");
        printTerminalLine();
    }

    private static void printExitMessage() {
        System.out.println("Fur-well friend, stay paw-sitive!");
        printTerminalLine();
    }

    private static void doAction(String userInput) {
        // markPattern can be moved to class field to reduce overhead
        Pattern markPattern = Pattern.compile("(mark|unmark)\\s+(\\d+)");
        String trimmedInput = userInput.trim().toLowerCase();
        Matcher matcher = markPattern.matcher(trimmedInput);

        if (matcher.matches()) {
            String command = matcher.group(1);
            int index = Integer.parseInt(matcher.group(2));

            if (command.equals("mark")) {
                markTaskAsDone(index);
            } else if (command.equals("unmark")) {
                markTaskAsUndone(index);
            }
        } else {
            switch (trimmedInput) {
                case "":
                    // do nothing
                    break;
                case "bye":
                    printExitMessage();
                    System.exit(0);
                    break;
                case "list":
                    printMemory();
                    break;
                default:
                    echoUserInput(userInput);
                    saveToMemory(userInput);
                    break;
            }
        }
    }

    private static void echoUserInput(String userInput) {
        printTerminalLine();
        System.out.println(userInput);
        printTerminalLine();
    }

    private static void saveToMemory(String userInput) {
        taskTable[taskTableRowCount] = new Task(userInput, false);
        taskTableRowCount++;
    }

    private static void markTaskAsDone(int index) {
        Task currTask = taskTable[index - 1]; // table is 0-indexed
        currTask.markAsDone();

        printTerminalLine();
        System.out.println("Meow! I’ve scratched this task off the list!");
        System.out.println(currTask.toString());
        printTerminalLine();
    }

    private static void markTaskAsUndone(int index) {
        Task currTask = taskTable[index - 1]; // table is 0-indexed
        currTask.markAsUndone();

        printTerminalLine();
        System.out.println("Mrrreow! I’ve batted this task back onto the list.");
        System.out.println(currTask.toString());
        printTerminalLine();
    }

    private static void printMemory() {
        printTerminalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskTableRowCount; i++) {
            int printedIndex = i + 1; // table is 0-indexed, but we print starting from 1
            System.out.println(printedIndex + "." + taskTable[i].toString());
        }
        printTerminalLine();
    }


}
