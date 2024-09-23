package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;

/**
 * Represents the user's request for help in using the chatbot.
 */
public class HelpCommand extends Command {
    private static final String[] DEFAULT_HELP_MESSAGE = new String[] {"welcome darlin'...",
            "here are a few of the things you can do with this bot.",
            "you can enter \"list\" to view all tasks you have at the moment.",
            "you can also enter \"todo\", \"deadline\", or \"event\" to create tasks of those types.",
            "once you've completed a task, you can \"mark\" it as done, or \"unmark\" it if you "
                    + "realise afterwards that it's not quite done yet.",
            "if you'd like, you can delete tasks that you no longer want to see with \"delete\".",
            "you can even find specific tasks with \"find\"!",
            "and lastly, if ya' ever get bored of lil' ol' me, you can enter \"bye\" to exit the program.",
            "",
            "looking forward to workin' with ya' honey.",
            "love, Rizzler."};
    private final String[] helpMessage;

    /**
     * Constructor for HelpCommand. Without a specific command to help with, this will return
     * a default greeting with a list of all available commands for the user.
     */
    public HelpCommand() {
        super();
        this.helpMessage = DEFAULT_HELP_MESSAGE;
    }

    /**
     * Alternative constructor for HelpCommand that specifies the command to help with.
     * @param commandName The command the user requires help with. Case-sensitive.
     */
    public HelpCommand(String commandName) {
        super(commandName);
        this.helpMessage = DEFAULT_HELP_MESSAGE;
    }

    /**
     * Returns a few lines of information that may be helpful to a new user.
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return Information on common commands and where to read the documentation.
     */
    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        return helpMessage;
    }
}
