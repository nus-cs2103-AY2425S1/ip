package jbot.command;

/**
 * Represents a command that displays help information to the user.
 * This command provides a list of available commands and their descriptions.
 * Implements {@link JBotCommand} to adhere to the command interface.
 */
public class HelpCommand implements JBotCommand {
    private static final HelpCommand instance = new HelpCommand();

    private HelpCommand() {
    }

    /**
     * Returns the singleton instance of {@link HelpCommand}.
     *
     * @return The singleton instance of {@link HelpCommand}.
     */
    public static HelpCommand getInstance() {
        return instance;
    }

    /**
     * Executes the Help command and returns a list of available commands
     * along with their descriptions.
     *
     * @param input The user input string. The input is ignored for the help command.
     * @return A string containing the help message with available commands and usage instructions.
     */
    @Override
    public String run(String input) {
        StringBuilder helpMessage = new StringBuilder();

        helpMessage.append("Here are the commands you can use:\n\n");

        helpMessage.append("1. `list`\n");
        helpMessage.append("   - Displays all tasks in your list.\n\n");

        helpMessage.append("2. `todo <task description>`\n");
        helpMessage.append("   - Adds a new To-Do task to your list.\n\n");

        helpMessage.append("3. `deadline <task description> /by <date>`\n");
        helpMessage.append("   - Adds a new Deadline task with the specified date.\n\n");

        helpMessage.append("4. `event <task description> /from <start> /to <end>`\n");
        helpMessage.append("   - Adds a new Event task with a start and end time.\n\n");

        helpMessage.append("5. `mark <task number>`\n");
        helpMessage.append("   - Marks the specified task as done.\n\n");

        helpMessage.append("6. `unmark <task number>`\n");
        helpMessage.append("   - Marks the specified task as not done.\n\n");

        helpMessage.append("7. `delete <task number>`\n");
        helpMessage.append("   - Deletes the specified task from your list.\n\n");

        helpMessage.append("8. `find <keyword>`\n");
        helpMessage.append("   - Finds and lists all tasks that contain the specified keyword.\n\n");

        helpMessage.append("9. `bye`\n");
        helpMessage.append("   - Exits the application.\n\n");

        helpMessage.append("You can use these commands to manage your tasks easily. Have fun!");

        return helpMessage.toString();
    }
}
