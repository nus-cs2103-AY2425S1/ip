package atlas;

import atlas.commands.Command;
import atlas.exceptions.AtlasException;
import atlas.parser.Parser;
import atlas.storage.Storage;
import atlas.tasks.TaskList;

/**
 * Represents the Atlas chatbot containing the methods to instantiate it and run it.
 */
public class Atlas {
    private final Storage storage;
    private TaskList tasks;

    /**
     * Initialises the Atlas chatbot with the filepath for Storage.
     *
     * @param filepath The filepath of the file where tasks will be loaded from and saved to.
     */
    public Atlas(String filepath) {
        this.storage = new Storage(filepath);
        this.tasks = new TaskList();
    }


    /**
     * Attempts to load a previously saved list of tasks. If there is any error, it will be caught and displayed.
     *
     * @return String The message to be displayed to the user when the chatbot is initialized.
     */
    public String init() {
        StringBuilder s = new StringBuilder();
        try {
            this.tasks = new TaskList(this.storage.load());
            s.append("Loaded previously saved tasks!");
        } catch (AtlasException e) {
            s.append("Failed to load saved tasks!");
        } finally {
            s.append("\n\n\n").append("Hello! I'm Atlas\n" + "What can I do for you?");
        }

        return s.toString();
    }

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
