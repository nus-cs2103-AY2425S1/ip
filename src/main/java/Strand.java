import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Strand {

    private static boolean running = false;

    private static final String horizontalLine = "----------------------------------------><>";

    private static final ArrayList<Task> strandList = new ArrayList<>();

    /**
     * Prints a string with an indentation of 4 spaces.
     *
     * @param s The string to be printed.
     */
    private static void print(String s) {
        System.out.println(s.indent(4));
    }

    /**
     * Prints a string surrounded by horizontal lines for output formatting.
     *
     * @param s The string to be printed between the horizontal lines.
     */
    private static void output(String s) {
        System.out.println(horizontalLine.indent(4));
        print(s);
        System.out.println(horizontalLine.indent(4));
    }

    /**
     * Initializes the chat by setting the running flag to true and printing a welcome message.
     */
    private static void chatStart() {
        running = true;
        output("Hello from Strand\nWhat can I do for you?");
    }

    /**
     * Generates a string representation of all tasks in the strandList.
     *
     * @return A string listing all tasks, each with its index and status icon.
     */
    private static String listAll() {
        return(
                strandList.stream()
                        .map((x) -> (strandList.indexOf(x) + 1) + "." + x.toString() + "\n")
                        .reduce((a, b) -> a + b).orElse("")
        );
    }

    /**
     * Marks a task as done or undone based on the input command.
     *
     * @param input The user command to mark or unmark a task.
     * @throws StrandException If the input is invalid or the task index is out of bounds.
     */
    private static void mark(String input) throws StrandException {
        String[] split = input.split("\\s+");
        if (split.length < 2) {
            throw new StrandNumberNotFoundException(split[0]);
        } else {
            try {
                int index = Integer.parseInt(split[1]);
                if (index > strandList.size() || index < 1) {
                    throw new StrandWrongIndexException(strandList.size());
                }
                Task t = strandList.get(index - 1);
                String str;
                if (Objects.equals(split[0], "mark")) {
                    t.markAsDone();
                    str = "Nice! I've marked this task as done:\n";
                } else {
                    t.markAsNotDone();
                    str = "OK, I've marked this task as not done yet:\n";
                }
                output(str + t.toString());

            } catch (NumberFormatException e) {
                throw new StrandNumberNotFoundException(split[0]);
            }
        }
    }

    /**
     * Adds a new task to the strandList based on the input command.
     *
     * @param input The user command to add a task.
     * @throws StrandException If the task description or necessary parameters are missing or incorrect.
     */
    private static void addTask(String input) throws StrandException {
        String[] split = input.split(" ", 2);
        String type = split[0];
        if (split.length < 2) {
            throw new StrandDescNotFoundException("Description", type);
        }
        String desc = split[1];
        if (desc.isEmpty()) {
            throw new StrandDescNotFoundException("Description", type);
        }
        switch (type.toLowerCase()) {
            case "todo": {
                strandList.add(new Todo(desc));
                break;
            }
            case "deadline": {
                if (!desc.contains(" /by ")) {
                    throw new StrandDescNotFoundException("Deadline", type);
                }
                String description = desc.substring(0, desc.indexOf(" /by "));
                String deadline = desc.substring(desc.indexOf(" /by ") + 5);
                strandList.add(new Deadline(description, deadline));
                break;
            }
            case "event": {
                if (!desc.contains(" /from ")) {
                    throw new StrandDescNotFoundException("Start time", type);
                }
                if (!desc.contains(" /to ")) {
                    throw new StrandDescNotFoundException("End time", type);
                }
                String description = desc.substring(0, desc.indexOf(" /from "));
                String start = desc.substring(desc.indexOf(" /from ") + 7, desc.indexOf(" /to "));
                String end = desc.substring(desc.indexOf(" /to ") + 5);
                strandList.add(new Event(description, start, end));
                break;
            }
        }
        output("Got it. I've added this task:\n  "
                + strandList.get(strandList.size() - 1).toString()
                + "\nNow you have " + strandList.size() + " tasks in the list.");
    }

    /**
     * Processes the user input and executes the corresponding command.
     *
     * @param input The user command.
     * @throws StrandException If the command is invalid or an error occurs during processing.
     */
    private static void inputs(String input) throws StrandException {
        if (input.equalsIgnoreCase("bye")) {
            output("Bye. Hope to see you again soon!");
            running = false;
        } else if (input.equalsIgnoreCase("list")) {
            output(listAll());
        } else if (input.toLowerCase().startsWith("mark") ||
                input.toLowerCase().startsWith("unmark")) {
            mark(input);
        } else if (input.toLowerCase().startsWith("todo") ||
                input.toLowerCase().startsWith("deadline") ||
                input.toLowerCase().startsWith("event")) {
            addTask(input);
        } else {
            throw new StrandWrongCommandException();
        }
    }

    public static void main(String[] args) {
        chatStart();
        Scanner scan = new Scanner(System.in);
        while (running && scan.hasNextLine()) {
            String userInput = scan.nextLine();
            try {
                inputs(userInput);
            } catch (StrandException e) {
                output(e.toString());
            }
        }
    }
}
