package yapbot.main;

import java.time.format.DateTimeParseException;

import yapbot.commands.Command;
import yapbot.exceptions.YapBotException;
import yapbot.util.Parser;
import yapbot.util.Storage;
import yapbot.util.TaskList;
import yapbot.util.Ui;

/**
 * Main class to start YapBot.
 */
public class YapBot {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Returns a YapBot instance.
     *
     * @param filepath Location where tasks are saved to.
     */
    public YapBot(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.taskList = new TaskList();
    }

    /**
     * Driver function to start the YapBot instance.
     */
    public void run() {
        ui.welcomeUser();

        // Tries to load up tasks from hard drive
        try {
            this.taskList = new TaskList(storage.load());
        } catch (YapBotException e) {
            ui.printError(e.getMessage());
        }

        boolean shouldExit = false;

        while (!shouldExit) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input);
                shouldExit = c.isExit();
                boolean isSuccess = c.execute(taskList, ui, storage);

            } catch (NumberFormatException e) {
                // Handles the case where user tries to specify task using 'one' instead of '1'
                ui.printError("Error, Natural Language Processing Module offline...\nSpecify "
                        + "Task number instead (eg. \"1\", \"2\").");
            } catch (YapBotException e) {
                ui.printError(e.getMessage());
            } catch (DateTimeParseException e) {
                // Handles the case where the user inputs date and time in invalid format
                ui.printError("Error, Dynamic DateTime Module offline."
                        + "\nDate & Time should be one of these formats:"
                        + "\n  Date & Time - \"5pm 2024/09/01\""
                        + "\n  Date Only (Time defaults to 8am) - \"2024/09/01\""
                        + "\n  Time Only (Date defaults to today) - \"5pm\"");
            }
        }
    }

    public static void main(String[] args) {
        new YapBot("data/tasks.txt").run();
    }
}
