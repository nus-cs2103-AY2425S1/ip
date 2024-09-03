package yapbot.main;

import yapbot.commands.Command;
import yapbot.exceptions.YapBotException;
import yapbot.util.Parser;
import yapbot.util.Storage;
import yapbot.util.TaskList;
import yapbot.util.Ui;

import java.time.format.DateTimeParseException;

/**
 * Main class to start YapBot.
 */
public class YapBot {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public YapBot(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.taskList = new TaskList();
    }

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
                        + "\n  Date Only - \"2024/09/01\""
                        + "\n  Time Only - \"5pm\"");
            }
        }
    }

    /**
     * Driver method to accept user input and parse commands.
     *
     * @param args Not needed.
     */
    public static void main(String[] args) {
        new YapBot("data/tasks.txt").run();
    }
}
