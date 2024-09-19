package rasputin;

import rasputin.command.Command;
import rasputin.command.InvalidCommand;

import rasputin.parser.Parser;

import rasputin.storage.Storage;

import rasputin.task.RasputinException;
import rasputin.task.TaskList;



/**
 * Represents a chatbot to store a list of tasks for the user.
 */
public class Rasputin {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Takes in the path of the .txt file containing the list of tasks to be read and written to.
     *
     * @param filePath Path of file to be opened.
     */
    public Rasputin(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.readFile());
        this.parser = new Parser(tasks, storage);
    }


    /**
     * Parses the input from the user, executes the command given and returns the bot's output message.
     * @param input String input from the user
     * @return String output of Rasputin
     */
    public String getResponse(String input) {
        try {
            Command cmd = parser.parseInput(input);
            return cmd.execute();
        } catch (RasputinException e) {
            return new InvalidCommand(e.getMessage()).execute();
        }

    }

}
