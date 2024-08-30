package WindeBot;

import Commands.Command;
import Commands.ListCommand;

import Exceptions.EmptyDescriptionException;
import Exceptions.TooManyParametersException;
import Exceptions.UnsupportedCommandException;

/**
 * The Winde class is the main class that runs the WindeBot chatbot.
 * It initializes the necessary components and handles the main program loop.
 */

public class Winde {

    private static History history;
    private static Reminder reminder;
    private static Ui ui;

    /**
     * Constructs a Winde object with the specified file path for storing history.
     *
     * @param filePath The file path for storing task history.
     */

    Winde(String filePath) {
        ui = new Ui();
        history = new History(filePath);
        reminder = new Reminder(history.load());
    }

    /**
     * Constructs a Winde object with the default file path for storing history.
     */

    Winde() {
        ui = new Ui();
        history = new History();
        reminder = new Reminder(history.load());
    }

    /**
     * The main method that starts the WindeBot application.
     *
     * @param args Command-line arguments (not used).
     */

    public static void main(String[] args) {
        new Winde().run();
    }

    /**
     * Starts the main loop of the WindeBot application.
     */

    private static void run() {
        ui.greet();
        Command currentCommand = new ListCommand();
        boolean shouldContinue = true;
        while (shouldContinue) {
            try {
                String input = ui.read();
                ui.showLine();
                currentCommand = Parser.parse(input);
                shouldContinue = currentCommand.execute(input, reminder, ui);
            } catch (UnsupportedCommandException e) {
                throw new RuntimeException(e);
            } catch (EmptyDescriptionException e) {
                throw new RuntimeException(e);
            } catch (TooManyParametersException e) {
                throw new RuntimeException(e);
            } finally {
                ui.showLine();
            }
        }
        currentCommand.exit(history, reminder, ui);
    }
}

