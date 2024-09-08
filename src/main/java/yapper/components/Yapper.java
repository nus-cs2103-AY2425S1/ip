package yapper.components;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import yapper.commands.CommandList;
import yapper.exceptions.YapperException;

/**
 * The main class for the Yapper chatbot that stores ToDos, Deadlines, and Events in a taskLit
 */
public class Yapper {

    private Storage storage;
    private Parser parser;
    private TaskList taskList;
    private Ui ui;
    private CommandList commandList;

    /**
     * Constructs a Yapper object with the specified file path for storage.
     *
     * @param filePath the file path for storing task data
     */
    public Yapper(String filePath) {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.readFile(), filePath);
        this.commandList = new CommandList();
    }

    /**
     * Generates a response for the user's chat message based on the command given.
     * If user types "bye", the program will display a message before closing itself.
     */
    public String getResponse(String input) {
        try {
            parser.parseLine(input);
            String command = parser.getCommand();
            return this.commandList.getCommandToExecute(command).execute(this.parser, this.taskList, this.storage);
        } catch (YapperException e) {
            return Ui.errorCaught(e.getMessage());
        }
    }
}
