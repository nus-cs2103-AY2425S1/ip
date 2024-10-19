package yapbot.main;

import java.io.IOException;
import java.time.format.DateTimeParseException;

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
     * Starts the {@code YapBot} instance and returns the appropriate startup message.
     */
    public String run() {
        String startupMessage = "Powering up...System booted successfully.\nYapBot online.\nExtensive damage detected."
                + "\nCore Systems 28% functional.";

        // Tries to load up tasks from hard drive
        try {
            this.taskList = new TaskList(storage.load());
            String successMessage = "\n\nSave data detected...loaded successfully."
                    + "\nYapBot will execute with data from previous instance.";
            return startupMessage + successMessage;
        } catch (YapBotException e) {
            return startupMessage + "\n\n" + e.getMessage();
        }

    }

    /**
     * Saves tasks to file to terminate the {@code YapBot} instance.
     *
     * @return true if tasks are saved and false otherwise.
     */
    public boolean close() {
        try {
            storage.save(taskList.saveTasks());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Changes the save file location for this {@code YapBot} instance.
     * Tries to load tasks (if any) from the specified file.
     * If unsuccessful, reverts back to original save file location and tasks.
     *
     * @param filepath Path of the file to load from and save to.
     * @return true if the save file location is changed and false otherwise.
     */
    public boolean changeLoadFile(String filepath) {
        Storage tempStorage = this.storage;
        TaskList tempTaskList = this.taskList;

        try {
            this.storage = new Storage(filepath);
            this.taskList = new TaskList(storage.load());
            return true;
        } catch (YapBotException e) {
            this.storage = tempStorage;
            this.taskList = tempTaskList;

            return false;
        }
    }

    /**
     * Returns the String response of {@code YapBot} based on input supplied.
     *
     * @param input User input
     * @return Response from {@code YapBot}
     *
     * @throws YapBotException If user input is invalid, eg. invalid command or insufficient arguments
     * @throws NumberFormatException When input uses strings instead of numbers, causing Integer parse to fail.
     * @throws DateTimeParseException When Dates and Times are not inputted in the correct format.
     */
    public String getResponse(String input) throws YapBotException, NumberFormatException, DateTimeParseException {
        assert this.taskList != null : "TaskList not instantiated";
        assert this.storage != null : "Storage not instantiated";

        Command c = Parser.parse(input, this);
        shouldExit = c.isExit();

        return c.execute(taskList, storage);
    }

    /**
     * Returns if this {@code YapBot} instance should be terminated.
     */
    public boolean shouldExit() {
        return this.shouldExit;
    }
}
