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
     *         Also return false if input == null.
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
        case "todo":
            addTodoTask(arguments);
            break;
        case "deadline":
            addDeadlineTask(arguments);
            break;
        case "event":
            addEventTask(arguments);
            break;
        default:
            noSuchCommand(command);
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

    private static void addTodoTask(String[] arguments) {
        String sample = "todo read book";

        if (arguments == null || arguments.length < 1) {
            String s = "Please specify a task description as argument.\n"
                    + "E.g. " + sample;
            new Message(s).print();
            return;
        }

        String description = String.join(" ", arguments);
        TodoTask newTask = new TodoTask(description);
        addTask(newTask);
    }

    private static void addDeadlineTask(String[] arguments) {
        String sample = "deadline return book /by Sunday";

        if (arguments == null) {
            String s = "Please specify a task description and a deadline as argument.\n"
                    + "E.g. " + sample;
            new Message(s).print();
            return;
        }

        int idx = findIndexOfStringInArray(arguments, "/by");
        
        if (idx < 0) {
            String s = "Please indicate a deadline using '/by'.\nE.g. " + sample;
            new Message(s).print();
            return;
        }

        if (idx == 0) {
            String s = "Please indicate a task description.\nE.g. " + sample;
            new Message(s).print();
            return;
        }

        if (idx == arguments.length - 1) {
            String s = "Please indicate a deadline after '/by'.\nE.g. " + sample;
            new Message(s).print();
            return;
        }

        String description = sliceAndJoinAt(arguments, 0, idx, " ");
        String deadline = sliceAndJoinAt(arguments, idx + 1, arguments.length, " ");
        DeadlineTask newTask = new DeadlineTask(description, deadline);
        addTask(newTask);
    }

    private static void addEventTask(String[] arguments) {
        String sample = "event project meeting /from Mon 2pm /to 4pm";

        if (arguments == null) {
            String s = "Please specify a task description, starting and ending time of event as argument.\n"
                    + "E.g. " + sample;
            new Message(s).print();
            return;
        }

        int fromIdx = findIndexOfStringInArray(arguments, "/from");
        int toIdx = findIndexOfStringInArray(arguments, "/to");

        if (fromIdx < 0 || toIdx < 0) {
            String s = "Please indicate starting and ending time of event using '/from' and '/to'.\n"
                    + "E.g. " + sample;
            new Message(s).print();
            return;
        }

        if (toIdx < fromIdx) {
            String s = "Please indicate the start time before the end time.\nE.g. " + sample;
            new Message(s).print();
            return;
        }

        if (fromIdx == 0) {
            String s = "Please indicate a task description.\nE.g. " + sample;
            new Message(s).print();
            return;
        }

        if (toIdx - fromIdx < 2) {
            String s = "Please indicate a start time.\nE.g. " + sample;
            new Message(s).print();
            return;
        }

        if (toIdx == arguments.length - 1) {
            String s = "Please indicate an end time.\nE.g. " + sample;
            new Message(s).print();
            return;
        }

        String description = sliceAndJoinAt(arguments, 0, fromIdx, " ");
        String from = sliceAndJoinAt(arguments, fromIdx + 1, toIdx, " ");
        String to = sliceAndJoinAt(arguments, toIdx + 1, arguments.length, " ");
        EventTask newTask = new EventTask(description, from, to);
        addTask(newTask);
    }
    
    private static void list() {
        String s = "";
        for (int i = 0; i < numTasks; i++) {
            int idx = i + 1;
            Task task = tasks[i];
            if (i == 0) {
                s = String.format("%d. %s", idx, task.toString());
            } else {
                s += String.format("\n%d. %s", idx, task.toString());
            }
        }
        new Message(s).print();
    }

    private static void mark(String[] arguments) {
        String sample = "mark 2";

        if (arguments == null) {
            String s = "Please call mark with an integer.\nE.g. " + sample;
            new Message(s).print();
            return;
        }

        if (arguments.length > 1) {
            String s = "Too many arguments. Please call mark with only 1 integer.\nE.g. " + sample;
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

            String s = "Nice! I've marked this task as done:\n" + task.toString();
            new Message(s).print();
        } catch (NumberFormatException e) {
            String s = "Invalid argument. Please call mark with an integer.\nE.g. " + sample;
            new Message(s).print();
        }
    }

    private static void unmark(String[] arguments) {
        String sample = "unmark 2";

        if (arguments == null) {
            String s = "Please call unmark with an integer.\nE.g. " + sample;
            new Message(s).print();
            return;
        }

        if (arguments.length > 1) {
            String s = "Too many arguments. Please call unmark with only 1 integer.\nE.g. " + sample;
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
                String s = String.format("Task %d is not complete.", idx);
                new Message(s).print();
                return;
            }

            String s = "Ok. I've marked this task as not done yet:\n" + task.toString();
            new Message(s).print();
        } catch (NumberFormatException e) {
            String s = "Invalid argument. Please call unmark with an integer.\nE.g. " + sample;
            new Message(s).print();
        }
    }

    private static void noSuchCommand(String command) {
        if (command == null) {
            return;
        }

        String s = "No command found for '" + command + "'.";
        new Message(s).print();
    }

    /**
     * Add the task into storage.
     *
     * @param task the task to be added.
     */
    private static void addTask(Task task) {
        if (numTasks >= SIZE) {
            String s = String.format("Maximum number of tasks exceeded.\nCan only store %d tasks.",
                    SIZE);
            new Message(s).print();
            return;
        }

        tasks[numTasks++] = task;
        String s = String.format("Got it. I've added this task:\n%s\nNow you have %d task in your list.",
                task.toString(), numTasks);
        new Message(s).print();
    }

    /**
     * Find and return the index of String s in a String array.
     *
     * @param array the string array to be searched.
     * @param s the string to search for.
     * @return the index of the String s in given array.
     *         Return -1 if no such String is found.
     */
    private static int findIndexOfStringInArray(String[] array, String s) {
        return Arrays.<String>asList(array).indexOf(s);
    }

    /**
     * Slice the array with range from and to, and then join the remaining array using given delimiter.
     *
     * @param array the string array to slice.
     * @param from the index of the array to slice from, inclusive.
     * @param to the index of the array to slice to, exclusive.
     * @param delimiter the delimiter that separates each element.
     * @return the resulting String after joining it using delimiter.
     */
    private static String sliceAndJoinAt(String[] array, int from, int to, CharSequence delimiter) {
        return String.join(delimiter, Arrays.<String>copyOfRange(array, from, to));
    }
}
