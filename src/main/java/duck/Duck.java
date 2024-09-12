package duck;

import duck.commands.CommandType;
import duck.commands.DeadlineCommand;
import duck.commands.DeleteCommand;
import duck.commands.DoAfterCommand;
import duck.commands.EventCommand;
import duck.commands.MarkCommand;
import duck.commands.TodoCommand;
import duck.commands.UnmarkCommand;
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

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Parser lineBuffer = new Parser(input);

        // TODO: Replace with case statements
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
            String response;

            if (CommandType.FIND.equalsName(command)) {
                String pattern = lineBuffer.getRemainingLine();
                TaskList filteredTasks = TASKS.filterTasksByPattern(pattern);
                response = "Here are the tasks in your list:\n"
                        + filteredTasks.toString();
            } else if (CommandType.MARK.equalsName(command)) {
                response = new MarkCommand(TASKS, lineBuffer).executeCommand();
            } else if (CommandType.UNMARK.equalsName(command)) {
                response = new UnmarkCommand(TASKS, lineBuffer).executeCommand();
            } else if (CommandType.DELETE.equalsName(command)) {
                response = new DeleteCommand(TASKS, lineBuffer).executeCommand();
            } else if (CommandType.TODO.equalsName(command)) {
                response = new TodoCommand(TASKS, lineBuffer).executeCommand();
            } else if (CommandType.DEADLINE.equalsName(command)) {
                response = new DeadlineCommand(TASKS, lineBuffer).executeCommand();
            } else if (CommandType.EVENT.equalsName(command)) {
                response = new EventCommand(TASKS, lineBuffer).executeCommand();
            } else if (CommandType.DOAFTER.equalsName(command)) {
                response = new DoAfterCommand(TASKS, lineBuffer).executeCommand();
            } else {
                response = formatAsResponse("Oops, I do not understand you.");
            }
            return formatAsResponse(response);
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
