package windebot;

import commands.Command;
import exceptions.EmptyDescriptionException;
import exceptions.TooManyParametersException;
import exceptions.UnsupportedCommandException;

/**
 * The Winde class is the main class that runs the WindeBot chatbot.
 * It initializes the necessary components and handles the main program loop.
 */

public class Winde {

    private static History history;
    private static Reminder reminder;
    //private static Ui ui;
    private String commandType;
    private boolean willContinue;


    /**
     * Constructs a Winde object with the specified file path for storing history.
     *
     * @param filePath The file path for storing task history.
     */

    Winde(String filePath) {
        //ui = new Ui();
        history = new History(filePath);
        reminder = new Reminder(history.load());
        commandType = "";
        willContinue = true;
    }

    /**
     * Constructs a Winde object with the default file path for storing history.
     */

    public Winde() {
        //ui = new Ui();
        history = new History();
        reminder = new Reminder(history.load());
        commandType = "";
    }

    /*
    /**
     * The main method that starts the WindeBot application.
     *
     * @param args Command-line arguments (not used).
     */
    /*
    public static void main(String[] args) {
        new Winde().run();
    }
    */

    /*
    /**
     * Starts the main loop of the WindeBot application.
     */
    /*
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
    */

    /**
     * The main method that reponds to the user's queries
     *
     * @param input User's input in the GUI
     */

    public String getResponse(String input) {
        try {
            Command currentCommand = Parser.parse(input);
            Ui ui = new Ui();
            willContinue = currentCommand.execute(input, reminder, ui, history);
            commandType = currentCommand.whatCommand();
            return ui.getOutput();
        } catch (UnsupportedCommandException e) {
            throw new RuntimeException(e);
        } catch (EmptyDescriptionException e) {
            throw new RuntimeException(e);
        } catch (TooManyParametersException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    public String getResponse(String... input) {
        try {
            for (String string : input) {
                Command currentCommand = Parser.parse(string);
                Ui ui = new Ui();
                willContinue = currentCommand.execute(string, reminder, ui, history);
                commandType = currentCommand.whatCommand();
                return ui.getOutput();
            }
        } catch (UnsupportedCommandException e) {
            throw new RuntimeException(e);
        } catch (EmptyDescriptionException e) {
            throw new RuntimeException(e);
        } catch (TooManyParametersException e) {
            throw new RuntimeException(e);
        }
    }
    */

    public String getCommandType() {
        return commandType;
    }

    /**
     * The hello greeting that plays at the start of booting
     * up the WindeBot gui
     */

    public String hello() {
        String output = "__        __              _" + "\n";
        output += "\\ \\      / /(_) _ __   __| | ___ " + "\n";
        output += " \\ \\ /\\ / / | || '_  \\/ _` |/ _ \\ " + "\n";
        output += "  \\ V  V /  | || | | || (_ || __/ " + "\n";
        output += "   \\_/\\_/   |_||_| |_|\\__,_|\\___| " + "\n";
        output += "Hello! I'm Winde\n" + "What can I do for you?" + "\n";
        return output;
    }

    public boolean getWillContinue() {
        return willContinue;
    }
}

