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
     * Driver function to start the YapBot instance.
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

    public boolean close() {
        try {
            new ByeCommand().execute(taskList, storage);
            return true;
        } catch (YapBotException e) {
            return false;
        }
    }

    public String getResponse(String input) throws YapBotException, NumberFormatException, DateTimeParseException {
        Command c = Parser.parse(input);
        shouldExit = c.isExit();

        return c.execute(taskList, storage);
    }

    public boolean shouldExit() {
        return this.shouldExit;
    }
}
