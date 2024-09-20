package duck;

import duck.commands.Command;
import duck.commands.CommandType;
import duck.utils.Formatter;

public class Duck {
    private static final String CHATBOT_NAME = "Duck";

    // Standard messages
    private static final String GREETING = String.format("Hello! I'm %s\n", CHATBOT_NAME)
            + "What can I do for you?";

    // List to store user inputs
    private static final TaskList TASKS = new TaskList();

    private static String formatAsResponse(String text) {
        return Formatter.indentText(text, 0);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Parser lineBuffer = new Parser(input);
        String response;

        // "bye" and "list" will only work if they are the only word in the line
        // e.g. "bye bye" would not work
        if (CommandType.BYE.equalsName(input) || CommandType.LIST.equalsName(input)) {
            CommandType commandType = CommandType.valueOf(input.toUpperCase());
            Command command = commandType.createCommand(TASKS, lineBuffer);
            response = command.executeCommand();
        } else {
            String commandString = lineBuffer.getWord();

            if (!CommandType.contains(commandString.toUpperCase())) {
                response = formatAsResponse("Oops, I do not understand you.");
            } else {
                CommandType commandType = CommandType.valueOf(commandString.toUpperCase());
                Command command = commandType.createCommand(TASKS, lineBuffer);
                response = command.executeCommand();
            }
        }

        return formatAsResponse(response);
    }

    public String getGreeting() {
        return GREETING;
    }

    public void loadTasks() {
        // Get saved tasks
        TASKS.getTaskListFromFile();
    }
}
