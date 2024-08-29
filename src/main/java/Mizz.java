import util.TaskList;
import util.Storage;
import util.Ui;
import util.Parser;
import util.Utility;
import MizzExceptions.MizzException;
import commands.Command;

public class Mizz {
    /** Name of the chat bot */
    private static final String NAME = "Mizz";

    /** Stores the past commands entered */
    private final TaskList usrTasks;
    /** Greeting to be printed */
    private final String greeting;
    /** Last command entered by the user */
    private String cmd;

    private final Storage storage;

    private final Ui ui;

    /**
     * Constructor for Mizz class. Initialises the Mizz object with defualt values
     * 
     * @param greeting The greeting msg to be printed.
     * @param exitMsg  The exit msg to be printed.
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
        String greeting = String.format(
                "Hello! I'm %s\n%sWhat can I do for you?", NAME, Utility.INDENT);
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
                Utility.prettyPrint(e.toString());
            }
            // bot.commandHandler(cmd);
        }
    }

    /**
     * Static method to print greeting.
     */
    private void greet() {
        this.ui.printResponse(this.greeting);
    }

    /**
     * Utility method to check if the bot should be exited.
     * 
     * @return true if cmd == "bye" else false
     */
    private boolean isExited() {
        return this.cmd.equals("bye");
    }
}
