package chacha.command;

import chacha.ChaCha;
import chacha.Storage;
import chacha.Ui;
import chacha.task.TaskList;

/**
 * Represents the command to return a help message to user.
 */
public class HelpCommand extends Command {
    private static final String OVERALL_HELP_MSG = "Welcome to ChaCha Help Centre!\n"
            + "Below are the commands you can input:\n";
    private static final String INDIVIDUAL_COMMAND_MSG = "This is how you should input the command:\n";
    private static final String BYE_COMMAND = "bye\n";
    private static final String DEADLINE_COMMAND = "deadline <description> /by <YYYY-MM-DD>\n";
    private static final String DELETE_COMMAND = "delete <index>\n";
    private static final String EVENT_COMMAND = "event <description> /<YYYY-MM-DD> "
            + "/from <start time> /to <end time>\n";
    private static final String FIND_COMMAND = "find <keyword>\n";
    private static final String HELP_COMMAND = "help <command>\n";
    private static final String LIST_COMMAND = "list\n";
    private static final String MARK_COMMAND = "mark <index>\n";
    private static final String TODO_COMMAND = "todo <description>\n";
    private static final String UNMARK_COMMAND = "unmark <index>\n";

    private static final String BYE_MSG = "Want to close ChaCha -- bye\n";
    private static final String DEADLINE_MSG = "Want to add Deadline -- deadline\n";
    private static final String DELETE_MSG = "Want to delete a task -- delete\n";
    private static final String EVENT_MSG = "Want to add Event -- event\n";
    private static final String FIND_MSG = "Want to find with keyword -- find\n";
    private static final String HELP_MSG = "Need help on specific commands -- help\n";
    private static final String LIST_MSG = "Want to get your list of tasks -- list\n";
    private static final String MARK_MSG = "Want to mark a task -- mark\n";
    private static final String TODO_MSG = "Want to add a To Do -- todo\n";
    private static final String UNMARK_MSG = "Want to unmark a task -- unmark\n";

    public HelpCommand(ChaCha chacha) {
        super(chacha);
    }

    /**
     * Returns the string representation of help message.
     *
     * @param userInput User input
     * @param storage Storage of ChaCha
     * @param ui UI of ChaCha
     * @param tasks List of tasks
     * @return String representation.
     */
    @Override
    public String execute(String userInput, Storage storage, Ui ui, TaskList tasks) {
        String[] cmd = userInput.split(" ");
        if (cmd.length == 1) {
            return OVERALL_HELP_MSG + BYE_MSG + DEADLINE_MSG + DELETE_MSG + EVENT_MSG + FIND_MSG + HELP_MSG
                    + LIST_MSG + MARK_MSG + TODO_MSG + UNMARK_MSG;
        } else {
            switch (cmd[1]) {
            case "bye":
                return BYE_MSG + INDIVIDUAL_COMMAND_MSG + BYE_COMMAND;
            case "deadline":
                return DEADLINE_MSG + INDIVIDUAL_COMMAND_MSG + DEADLINE_COMMAND;
            case "delete":
                return DELETE_MSG + INDIVIDUAL_COMMAND_MSG + DELETE_COMMAND;
            case "event":
                return EVENT_MSG + INDIVIDUAL_COMMAND_MSG + EVENT_COMMAND;
            case "find":
                return FIND_MSG + INDIVIDUAL_COMMAND_MSG + FIND_COMMAND;
            case "help":
                return HELP_MSG + INDIVIDUAL_COMMAND_MSG + HELP_COMMAND;
            case "list":
                return LIST_MSG + INDIVIDUAL_COMMAND_MSG + LIST_COMMAND;
            case "mark":
                return MARK_MSG + INDIVIDUAL_COMMAND_MSG + MARK_COMMAND;
            case "todo":
                return TODO_MSG + INDIVIDUAL_COMMAND_MSG + TODO_COMMAND;
            case "unmark":
                return UNMARK_MSG + INDIVIDUAL_COMMAND_MSG + UNMARK_COMMAND;
            default:
                return "Hmm... ChaCha does not recognise this command. Please try another one!";
            }
        }
    }
}
