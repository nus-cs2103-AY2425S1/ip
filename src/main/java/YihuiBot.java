import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Your friendly todolist bot.
 *
 * @author Toh Yi Hui A0259080A
 */
public class YihuiBot {
    // The name of this bot
    private static final String NAME = "YihuiBot";

    // The maximum number of tasks that can be stored
    private static final int SIZE = 100;

    private static Task[] tasks;
    private static int numTasks;

    public static void main(String[] args) {
        reset();

        greetings();

        Scanner userInput = new Scanner(System.in);
        try {
            while (true) {
                String input = userInput.nextLine();
                if (!callSuitableFunction(input)) {
                    break;
                }
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println("An error has occured.");
        }

        exit();
    }

    private static void reset() {
        tasks = new Task[SIZE];
        numTasks = 0;
    }

    /**
     * Calls a suitable function based on the user input.
     *
     * @param input the user's input.
     * @return false if the program should be terminated (i.e. input == bye).
     *         Return true otherwise.
     */
    private static boolean callSuitableFunction(String input) {
        if (input == null) {
            return false;
        }

        String[] inputArray = input.split(" ");
        String command = inputArray.length < 1 ? "" : inputArray[0];
        String[] arguments = inputArray.length < 2 ? null : Arrays.copyOfRange(inputArray, 1, inputArray.length);

        switch (command) {
        case "bye":
            return false;
        case "":
            noInput();
            break;
        case "list":
            list();
            break;
        case "mark":
            mark(arguments);
            break;
        case "unmark":
            unmark(arguments);
            break;
        default:
            addTask(input);
            break;
        }

        return true;
    }

    private static void greetings() {
        String s = String.format(
            "Hello! I am %s!\nWhat can I do for you?",
            NAME
        );
        new Message(s).print();
    }

    private static void exit() {
        String s = "Bye. Hope to see you again soon!";
        new Message(s).print();
    }

    private static void noInput() {
        String s = "Please provide an input.";
        new Message(s).print();
    }

    private static void addTask(String input) {
        if (numTasks >= SIZE) {
            String s = String.format(
                "Maximum number of tasks exceeded.\nCan only store %d tasks.",
                SIZE
            );
            new Message(s).print();
            return;
        }

        tasks[numTasks++] = new Task(input);
        String s = "added: " + input;
        new Message(s).print();
    }

    private static void list() {
        String s = "";
        for (int i = 0; i < numTasks; i++) {
            int idx = i + 1;
            Task task = tasks[i];
            String isComplete = task.isComplete() ? "X" : " ";
            if (i == 0) {
                s = String.format("%d. [%s] %s", idx, isComplete, task.toString());
            } else {
                s += String.format("\n%d. [%s] %s", idx, isComplete, task.toString());
            }
        }
        new Message(s).print();
    }

    private static void mark(String[] arguments) {
        if (arguments == null) {
            String s = "Please call mark with an integer (e.g. mark 2).";
            new Message(s).print();
            return;
        }

        if (arguments.length > 1) {
            String s = "Too many arguments. Please call mark with only 1 integer.";
            new Message(s).print();
            return;
        }

        try {
            int idx = Integer.parseInt(arguments[0]);

            if (idx < 1 || idx > numTasks) {
                String s = "Invalid argument. Please specify a good index.";
                new Message(s).print();
                return;
            }

            Task task = tasks[idx - 1];
            if (!task.markComplete()) {
                String s = String.format("Task %d is already completed.", idx);
                new Message(s).print();
                return;
            }

            String s = "Nice! I've marked this task as done:\n[X] " + task.toString();
            new Message(s).print();
        } catch (NumberFormatException e) {
            String s = "Invalid argument. Please call mark with an integer (e.g. mark 2).";
            new Message(s).print();
        }
    }

    private static void unmark(String[] arguments) {
        if (arguments == null) {
            String s = "Please call unmark with an integer (e.g. mark 2).";
            new Message(s).print();
            return;
        }

        if (arguments.length > 1) {
            String s = "Too many arguments. Please call unmark with only 1 integer.";
            new Message(s).print();
            return;
        }

        try {
            int idx = Integer.parseInt(arguments[0]);
            
            if (idx < 1 || idx > numTasks) {
                String s = "Invalid argument. Please specify a good index.";
                new Message(s).print();
                return;
            }

            Task task = tasks[idx - 1];
            if (!task.markIncomplete()) {
                String s = String.format("Task %d is not completed.", idx);
                new Message(s).print();
                return;
            }

            String s = "Ok. I've marked this task as not done yet:\n[ ] " + task.toString();
            new Message(s).print();
        } catch (NumberFormatException e) {
            String s = "Invalid argument. Please call unmark with an integer (e.g. mark 2).";
            new Message(s).print();
        }
    }
}
