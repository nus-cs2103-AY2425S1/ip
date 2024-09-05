package atlas;

import atlas.commands.Command;
import atlas.exceptions.AtlasException;
import atlas.parser.Parser;
import atlas.storage.Storage;
import atlas.tasks.TaskList;
import atlas.ui.DialogBox;

/**
 * Represents the Atlas chatbot containing the methods to instantiate it and run it.
 */
public class Atlas {
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
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (AtlasException e) {
//            this.ui.showErrorMessage(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts the chatbot which greets the user. The chatbot will read the command, parse and execute
     * it until the user exits the chatbot. If there is any error, it will be caught and displayed.
     */
//    public void run() {
//        this.ui.showWelcomeMessage();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                Command c = Parser.parse(fullCommand);
//                c.execute(this.tasks, this.ui, this.storage);
//                isExit = c.isExit();
//            } catch (AtlasException e) {
//                this.ui.showErrorMessage(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
//    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.tasks, this.storage);
        } catch (AtlasException e) {
            return e.getMessage();
        }
    }
}
