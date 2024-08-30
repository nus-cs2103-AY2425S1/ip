package atlas;

import atlas.commands.Command;
import atlas.exceptions.AtlasException;
import atlas.parser.Parser;
import atlas.storage.Storage;
import atlas.tasks.TaskList;
import atlas.ui.Ui;

/**
 * Represents the Atlas chatbot containing the methods to instantiate it and run it.
 */
public class Atlas {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Initialises the Atlas chatbot with the filepath for Storage. Creates a new Ui instance.
     * Attempts to load a previously saved list of tasks. If there are no previous tasks then it
     * initialises an empty ArrayList. If there is any error, it will be caught and displayed.
     *
     * @param filepath The filepath of the file where tasks will be loaded from and saved to.
     */
    public Atlas(String filepath) {
        this.storage = new Storage(filepath);
        this.ui = new Ui();

        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (AtlasException e) {
            this.ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts the chatbot which greets the user. The chatbot will read the command, parse and execute
     * it until the user exits the chatbot. If there is any error, it will be caught and displayed.
     */
    public void run() {
        this.ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (AtlasException e) {
                this.ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Starts the chatbot.
     *
     * @param args Arguments provided to the command line.
     */
    public static void main(String[] args) {
        new Atlas("./data/atlas.txt").run();
    }
}
