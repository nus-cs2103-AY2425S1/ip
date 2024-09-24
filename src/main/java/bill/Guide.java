package bill;

/**
 * The guide class handles the description of the user guide when they request for help.
 */
public class Guide {
    private static final String DOUBLE_LINE_SPACE = "\n\n";
    private static final String GUIDE_GREET = "Howdy you seem a little lost,"
                + " these are the following commands you could use:";
    private static final String GUIDE_DESCRIPTION = "Below is a brief description of what each command can do."
                + " To get the exact formatting simply type in one of these commands"
                + " and ill show you how to format that command :D";
    private static final String COMMAND_HEADER = "COMMANDS";
    private static final String UNDERLINE = "--------------------------------";
    private static final String TODO_DESCRIPTION = "todo: Add a task you intend to do to your list.";
    private static final String DEADLINE_DESCRIPTION = "deadline: Add a deadline task with a formatted date.";
    private static final String EVENT_DESCRIPTION = "event: Add an event task, with 'from' and 'to' descriptions.";
    private static final String LIST_DESCRIPTION = "list: View your list of tasks.";
    private static final String FIND_DESCRIPTION = "find: Filter your list by description.";
    private static final String MARK_DESCRIPTION = "mark: Mark a task as complete.";
    private static final String UNMARK_DESCRIPTION = "unmark: Unmark a task as incomplete.";
    private static final String DELETE_DESCRIPTION = "delete: Remove a task from your list.";
    private static final String HELP_DESCRIPTION = "help: See an overview of available commands.";
    private static final String BYE_DESCRIPTION = "bye: Exit the chatbot.";
    private static final String GUIDE_CONCLUSION = "These are all my commands for now!";

    private static final String[] COMMAND_DESCRIPTIONS = {
        GUIDE_GREET,
        GUIDE_DESCRIPTION,
        COMMAND_HEADER,
        UNDERLINE,
        TODO_DESCRIPTION,
        DEADLINE_DESCRIPTION,
        EVENT_DESCRIPTION,
        LIST_DESCRIPTION,
        FIND_DESCRIPTION,
        MARK_DESCRIPTION,
        UNMARK_DESCRIPTION,
        DELETE_DESCRIPTION,
        HELP_DESCRIPTION,
        BYE_DESCRIPTION,
        UNDERLINE,
        GUIDE_CONCLUSION
    };


    /**
     * Initializes Guide.
     *
     */
    public Guide() {
    }

    /**
     * Generates a help guide with all possible commands.
     *
     * @return Formatted help guide of Bill bot.
     */
    public String generateGuide() {
        return String.join(DOUBLE_LINE_SPACE, COMMAND_DESCRIPTIONS);
    }
}
