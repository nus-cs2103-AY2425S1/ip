package duck;

import java.time.format.DateTimeParseException;

import duck.commands.CommandType;
import duck.commands.EventCommand;
import duck.exceptions.DeadlineUsageException;
import duck.exceptions.TodoUsageException;
import duck.tasks.DateAndTime;
import duck.tasks.Deadline;
import duck.tasks.Task;
import duck.tasks.Todo;
import duck.utils.Formatter;

public class Duck {
    private static final String CHATBOT_NAME = "Duck";

    // Standard messages
    private static final String GREETING = String.format("Hello! I'm %s\n", CHATBOT_NAME)
            + "What can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";

    // List to store user inputs
    private static final TaskList TASKS = new TaskList();

    private static String formatAsResponse(String text) {
        return Formatter.indentText(text, 4);
    }

    private static String handleNewTask(Task task) {
        TASKS.addTask(task);
        String response = "Got it. I've added this task:\n"
                + Formatter.indentText(task.toString(), 2) + "\n"
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
        if (CommandType.BYE.equalsName(input)) {
            return formatAsResponse(GOODBYE);
        } else if (CommandType.LIST.equalsName(input)) {
            String response = "Here are the tasks in your list:\n"
                    + Formatter.indentText(TASKS.toString(), 4);
            return formatAsResponse(response);
        } else {
            String command = lineBuffer.getWord();

            // "mark" / "unmark" commands
            if (CommandType.FIND.equalsName(command)) {
                String pattern = lineBuffer.getRemainingLine();
                TaskList filteredTasks = TASKS.filterTasksByPattern(pattern);
                String response = "Here are the tasks in your list:\n"
                        + filteredTasks.toString();
                return formatAsResponse(response);
            } else if (CommandType.MARK.equalsName(command)) {
                int taskLabel = lineBuffer.getInt();
                Task task = TASKS.getItem(taskLabel);
                task.markAsDone();
                TASKS.updateFileWithTaskList();

                String response = "Nice! I've marked this task as done:\n"
                        + Formatter.indentText(task.toString(), 2);
                return formatAsResponse(response);
            } else if (CommandType.UNMARK.equalsName(command)) {
                int taskLabel = lineBuffer.getInt();
                Task task = TASKS.getItem(taskLabel);
                task.markAsNotDone();
                TASKS.updateFileWithTaskList();

                String response = "OK, I've marked this task as not done yet:\n"
                        + Formatter.indentText(task.toString(), 2);
                return formatAsResponse(response);
            } else if (CommandType.DELETE.equalsName(command)) {
                // Delete
                int taskLabel = lineBuffer.getInt();
                Task task = TASKS.getItem(taskLabel);
                TASKS.removeItem(taskLabel);

                String response = "Noted. I've removed this task:\n"
                        + Formatter.indentText(task.toString(), 2) + "\n"
                        + String.format("Now you have %s tasks in the list.", TASKS.getTaskCount());
                return formatAsResponse(response);
            } else if (CommandType.TODO.equalsName(command)) {
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
            } else if (CommandType.DEADLINE.equalsName(command)) {
                String taskPart = lineBuffer.getUntilAndRemovePattern("/by");
                String deadlinePart = lineBuffer.getRemainingLine();
                try {
                    Task task = new Deadline(taskPart, new DateAndTime(deadlinePart));
                    String response = handleNewTask(task);
                    return response;
                } catch (DeadlineUsageException e) {
                    // TODO: friendly error message
                    return formatAsResponse(e.toString());
                } catch (DateTimeParseException e) {
                    // TODO: friendly error message
                    return formatAsResponse(e.toString());
                }
            } else if (CommandType.EVENT.equalsName(command)) {
                String response = new EventCommand(TASKS, lineBuffer).executeCommand();
                return formatAsResponse(response);
            } else {
                return formatAsResponse("Oops, I do not understand you.");
            }
        }
    }

    public String getGreeting() {
        return GREETING;
    }

    public void loadTasks() {
        // Get saved tasks
        TASKS.getTaskListFromFile();
    }
}
