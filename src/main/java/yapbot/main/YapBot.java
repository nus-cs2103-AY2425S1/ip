package yapbot.main;

import java.time.format.DateTimeParseException;

import yapbot.commands.ByeCommand;
import yapbot.commands.Command;
import yapbot.exceptions.YapBotException;
import yapbot.util.Parser;
import yapbot.util.Storage;
import yapbot.util.TaskList;

/**
 * Main class to start YapBot.
 */
public class YapBot {
    private TaskList taskList;
    private Storage storage;
    private boolean shouldExit;

    /**
     * Returns a YapBot instance.
     *
     * @param filepath Location where tasks are saved to.
     */
    public YapBot(String filepath) {
        this.storage = new Storage(filepath);
        this.taskList = new TaskList();
        this.shouldExit = false;
    }

    /**
     * Starts the YapBot instance and returns the appropriate startup message.
     */
    public String run() {

        // Tries to load up tasks from hard drive
        try {
            this.taskList = new TaskList(storage.load());
        } catch (YapBotException e) {
            return e.getMessage();
        }
        return "Powering up...System booted successfully.\nYapBot online.\nExtensive damage detected."
                + "\nCore Systems 28% functional.";
    }

    /**
     * Saves tasks to file.
     * Intended for use when YapBot app is not closed using ByeCommand.
     *
     * @return true if tasks are saved and false otherwise.
     */
    public boolean close() {
        try {
            new ByeCommand().execute(taskList, storage);
            return true;
        } catch (YapBotException e) {
            return false;
        }
    }

    /**
     * Returns the String response of YapBot based on input supplied.
     *
     * @param input User input
     * @return Response from YapBot
     *
     * @throws YapBotException If user input is invalid, eg. invalid command or insufficient arguments
     * @throws NumberFormatException When input uses strings instead of numbers, causing Integer parse to fail.
     * @throws DateTimeParseException When Dates and Times are not inputted in the correct format.
     */
    public String getResponse(String input) throws YapBotException, NumberFormatException, DateTimeParseException {
        Command c = Parser.parse(input);
        shouldExit = c.isExit();

        return c.execute(taskList, storage);
    }

    /**
     * Returns if this YapBot instance should be terminated.
     */
    public boolean shouldExit() {
        return this.shouldExit;
    }
}
