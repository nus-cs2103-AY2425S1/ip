import exceptions.DeadlineUsageException;
import exceptions.EventUsageException;
import exceptions.TodoUsageException;
import tasks.DateAndTime;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Duck {
    private static final String CHATBOT_NAME = "Duck";
    private static final String TEXT_SEPARATOR = "____________________________________________________________";
    private static final String TEXT_SEPARATOR_WITH_NEWLINE = TEXT_SEPARATOR + "\n";

    // Standard messages
    private static final String GREETING = String.format("Hello! I'm %s\n", CHATBOT_NAME)
            + "What can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";

    // List to store user inputs
    private static final TaskList TASKS = new TaskList();

    private static String addSeparators(String text) {
        return TEXT_SEPARATOR_WITH_NEWLINE
                + text + "\n"
                + TEXT_SEPARATOR_WITH_NEWLINE;
    }

    private static String indentText(String text, int indentLevel) {
        StringBuilder indentedText = new StringBuilder();
        String[] lines = text.split("\n");

        for (String line : lines) {
            indentedText.append(" ".repeat(indentLevel)).append(line).append("\n");
        }

        // Convert to String and trim the last newline character
        return indentedText.toString().replaceAll("[\n\r]$", "");
    }

    private static String formatAsResponse(String text) {
        return indentText(addSeparators(indentText(text, 1)), 4);
    }

    private static void printAsResponse(String text) {
        System.out.println(formatAsResponse(text));
        System.out.println("");
    }

    private static String handleNewTask(Task task) {
        TASKS.addTask(task);
        String response = "Got it. I've added this task:\n"
                + indentText(task.toString(), 2) + "\n"
                + "Now you have " + TASKS.getTaskCount() + " tasks in the list.";
        return formatAsResponse(response);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Parser lineBuffer = new Parser(input);

        // "bye" and "list" will only work if they are the only word in the line
        // e.g. "bye bye" would not work
        if (Command.BYE.equalsName(input)) {
            return formatAsResponse(GOODBYE);
        } else if (Command.LIST.equalsName(input)) {
            String response = "Here are the tasks in your list:\n"
                    + TASKS.toString();
            return formatAsResponse(response);
        } else {
            String command = lineBuffer.getWord();

            // "mark" / "unmark" commands
            if (Command.FIND.equalsName(command)) {
                String pattern = lineBuffer.getRemainingLine();
                TaskList filteredTasks = TASKS.filterTasksByPattern(pattern);
                String response = "Here are the tasks in your list:\n"
                        + filteredTasks.toString();
                return formatAsResponse(response);
            } else if (Command.MARK.equalsName(command)) {
                int taskLabel = lineBuffer.getInt();
                Task task = TASKS.getItem(taskLabel);
                task.markAsDone();
                TASKS.updateFileWithTaskList();

                String response = "Nice! I've marked this task as done:\n"
                        + indentText(task.toString(), 2);
                return formatAsResponse(response);
            } else if (Command.UNMARK.equalsName(command)) {
                int taskLabel = lineBuffer.getInt();
                Task task = TASKS.getItem(taskLabel);
                task.markAsNotDone();
                TASKS.updateFileWithTaskList();

                String response = "OK, I've marked this task as not done yet:\n"
                        + indentText(task.toString(), 2);
                return formatAsResponse(response);
            } else if (Command.DELETE.equalsName(command)) {
                // Delete
                int taskLabel = lineBuffer.getInt();
                Task task = TASKS.getItem(taskLabel);
                TASKS.removeItem(taskLabel);

                String response = "Noted. I've removed this task:\n"
                        + indentText(task.toString(), 2) + "\n"
                        + String.format("Now you have %s tasks in the list.", TASKS.getTaskCount());
                return formatAsResponse(response);
            } else if (Command.TODO.equalsName(command)) {
                // Tasks
                // TODO: Reduce duplicate code

                String taskPart = lineBuffer.getRemainingLine();
                try {
                    Task task = new Todo(taskPart);
                    String response = handleNewTask(task);
                    return response;
                } catch (TodoUsageException e) {
                    return formatAsResponse(e.toString());
                }
            } else if (Command.DEADLINE.equalsName(command)) {
                String taskPart = lineBuffer.getUntilAndRemovePattern("/by");
                String deadlinePart = lineBuffer.getRemainingLine();
                try {
                    Task task = new Deadline(taskPart, new DateAndTime(deadlinePart));
                    String response = handleNewTask(task);
                    return response;
                } catch (DeadlineUsageException e) {
                    return formatAsResponse(e.toString());
                }
            } else if (Command.EVENT.equalsName(command)) {
                String taskPart = lineBuffer.getUntilAndRemovePattern("/from");
                String fromPart = lineBuffer.getUntilAndRemovePattern("/to");
                String toPart = lineBuffer.getRemainingLine();
                try {
                    Task task = new Event(taskPart, new DateAndTime(fromPart), new DateAndTime(toPart));
                    String response = handleNewTask(task);
                    return response;
                } catch (EventUsageException e) {
                    return formatAsResponse(e.toString());
                }
            } else {
                return formatAsResponse("Oops, I do not understand you.");
            }
        }
    }

    public static void main(String[] args) {
        // Get saved tasks
        TASKS.getTaskListFromFile();

        // // Start scanner
        // Scanner sc = new Scanner(System.in);

        // // Print greeting
        // printAsResponse(GREETING);

        // // User input loop
        // while (true) {
        // String line = sc.nextLine();

        // }

        // // Close Scanner
        // sc.close();
    }
}
