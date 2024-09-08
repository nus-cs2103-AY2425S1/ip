package infinity.ui;

import infinity.task.Task;
import infinity.tasklist.TaskList;

/**
 * This class handles the user interface of the bot.
 */
public class Ui {

    /** Name of the Bot. */
    public static final String BOT_NAME = "Infinity";
    /** What the bot says when it shutdowns. */
    public static final String BOT_END_STATEMENT = "Well, if you are leaving, then I must be infinitely too dumb :(\n";
    /** What the bot says when there is an invalid command. */
    private static final String BOT_INVALID_COMMAND = "Wait a minute, that's not something I recognise...\n";
    /** Just a breakline. */
    private static final String BREAKLINE = "\n";
    /** Bot reply for not a number */
    private static final String BOT_EMPTY_TASKLIST =
            "Task list is empty :(\n";
    /** Bot reply for not a number */
    private static final String BOT_SHOW_TASKLIST =
            "Here's your Tasklist:\n";
    /** String output for help command. */
    private static final String COMMAND_STRING = """
            fields in <> are to be populated as appropriate
            •• Multi operations supported, use && to separate

            • bye
                -> Shutdown the bot
            •• deadline <task> /by <date in "DD/MM/YYYY HHMM">
                -> Add a Deadline task
            •• delete <index of task>
                -> Delete a task
            •• event <task> /from <period> /to <period>
                -> Add an Event task
            •• find <keyword>
                -> Finds all tasks and shows them with the keyword given
            • help
                -> Show all available commands
            • list
                ->  all tasks
            •• mark <index of task>
                -> Marks a task as done
            • save
                -> Saves tasklist manually
            •• todo <task>
                -> Add a Todo task\n""";

    /**
     * Prepends the bot name to the input.
     *
     * @param input The String input to prepend the bot name to.
     * @return The input with the bot name prepended.
     */
    public static String prependBotName(String input) {
        return String.format("\n%s\n--------\n%s", BOT_NAME, input);
    }

    /**
     * Prints the bot's response.
     *
     * @param input The String input to print.
     * @return The bot output with the bot name above.
     */
    public static String botSays(String input) {
        return prependBotName(input) + BREAKLINE;
    }

    /**
     * Prints the bot's response whilst being able to choose whether to print with a break line.
     *
     * @param input The String input to print.
     * @param isBreakLine Whether to print a break line after the input.
     * @return The bot output with or without a breakline.
     */
    public static String botSays(String input, boolean isBreakLine) {
        return prependBotName(input) + (isBreakLine ? BREAKLINE : "");
    }

    /**
     * Lists the tasks in the task list.
     *
     * @param tasks The task list to list.
     * @return The bot output for the tasks given.
     */
    public static String listTasks(TaskList tasks) {
        StringBuilder finalOutput = new StringBuilder();

        if (tasks.isEmpty()) {
            finalOutput.append(BOT_EMPTY_TASKLIST);
        } else {
            finalOutput.append(BOT_SHOW_TASKLIST);

            int i = 1;
            for (Task task : tasks.getTasks()) {
                finalOutput.append(String.format("    %d. %s\n", i, task.toString()));
                i++;
            }
        }
        return finalOutput.toString();
    }

    /**
     * List the single task provided.
     *
     * @param task The task list to list.
     * @param i The index of the task.
     * @return The bot output for the task given.
     */
    public static String listTask(Task task, int i) {
        return String.format("    %d. %s", i, task.toString());
    }

    /**
     * Invalid command received
     *
     * @return The message saying that the command was not recognised
     */
    public static String invalidCommand() {
        return BOT_INVALID_COMMAND;
    }

    /**
     * Gets the list of available commands.
     *
     * @return The list of commands.
     */
    public static String getCommands() {
        return COMMAND_STRING;
    }

    /**
     * Ends the bot and program.
     *
     * @return The bot saying it is the end.
     */
    public static String endBot() {
        return BOT_END_STATEMENT;
    }
}
