/**
 * The main class for the Fishman bot.
 * This class initializes the user interface, task list and parser,
 * and manages the main program.
 */
public class Fishman {
    /** The user interface object for handling input and output operations. */
    private final Ui ui;
    /** The task list object to store and manage tasks. */
    private final TaskList tasks;
    /** The parser object to interpret user inputs. */
    private final Parser parser;

    /**
     * Constructs a new instance of Fishman
     * Initializes the UI, task list and parser.
     */
    public Fishman() {
        ui = new Ui();
        tasks = new TaskList();
        parser = new Parser();
    }

    /**
     * Starts the Fishman bot.
     * Displays the logo and welcome message, before entering the main loop.
     * The loop will continue until the exit command is received.
     */
    public void start() {
        ui.displayLogo();
        ui.displayWelcome();
        boolean isExit = false;

        while (!isExit) {
            String userInput = ui.readCommands();
            Command command = Parser.parse(userInput);
            command.execute(tasks,ui);
            isExit = command.isExit();
        }
    }

    /**
     * The main entry point for Fishman bot.
     * Creates a new Fishman instance and starts the bot.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Fishman().start();
    }
}

