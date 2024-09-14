package mizz;

import static util.Utility.INDENT;
import static util.Utility.prettyPrint;

import commands.Command;
import exceptions.MizzException;
import util.Parser;
import util.Storage;
import util.TaskList;
import util.Ui;


/**
 * Main chat bot class.
 */
public class Mizz {
    /** Name of the chat bot */
    private static final String NAME = "Mizz";
    /** Stores the past commands entered */
    private final TaskList usrTasks;
    /** Greeting to be printed */
    private final String greeting;
    /** Last command entered by the user */
    private String cmd;
    /** Storage class to interact with the hard disk */
    private final Storage storage;
    /** Ui class to interact with the user */
    private final Ui ui;

    /**
     * Constructor for Mizz class. Initialises the Mizz object with defualt values
     *
     * @param greeting The greeting msg to be printed.
     * @param exitMsg The exit msg to be printed.
     */
    public Mizz(String greeting, String filePath) {
        this.greeting = greeting;
        this.usrTasks = new TaskList();
        this.cmd = "";
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            Parser.parseFromStorage(this.storage).forEach((t) -> {
                this.usrTasks.addTask(t);
            });
        } catch (MizzException e) {
            this.ui.printResponse("Error adding task from file: ", e.toString());
        }
    }

    public static void main(String[] args) {
        String greeting = String.format("Hello! I'm %s\n%sWhat can I do for you?", NAME, INDENT);
        Mizz bot = new Mizz(greeting, "./store/storage.txt");

        bot.greet();

        while (!bot.isExited()) {
            String cmd = bot.ui.getNextLine();
            try {
                String[] parsedInput = Parser.parseStringInput(cmd);
                bot.cmd = parsedInput[0];
                Command c = Command.of(bot.cmd);
                c.execute(bot.usrTasks, bot.ui, bot.storage, parsedInput);
            } catch (MizzException e) {
                prettyPrint(e.toString());
            }
        }
    }

    /**
     * Prints greeting.
     */
    private void greet() {
        this.ui.printResponse(this.greeting);
    }

    /**
     * Checks if bot is exited.
     *
     * @return true if cmd == "bye" else false
     */
    public boolean isExited() {
        return this.cmd.equals("bye");
    }

    /**
     * Returns the appropriate response for the given input.
     *
     * @param input The input by the user.
     * @return The response for the given input.
     */
    public String getResponse(String input) {
        try {
            String[] parsedInput = Parser.parseStringInput(input);
            this.cmd = parsedInput[0];
            Command c = Command.of(this.cmd);
            c.execute(usrTasks, ui, storage, parsedInput);
        } catch (MizzException e) {
            this.ui.setResponse("Error occured: " + e.toString());
        }
        return this.ui.getResponse();
    }
}
