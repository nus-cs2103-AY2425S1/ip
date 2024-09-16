package snah.commands;

import snah.TaskList;
import snah.errors.ParsingException;
import snah.util.Storage;

/**
 * Abstract class to handle commands that are created from the parser
 */
public abstract class Command {

    private String input;

    /**
     * Constructor for the command
     * @param input User input
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Executes the command
     * @param tasks Task list
     * @return Response from the command
     * @throws ParsingException If there is an error parsing the command
     */
    public abstract String execute(TaskList tasks, Storage storage) throws ParsingException;

    /**
     * Returns the user input
     * @return User input
     */
    public String getInput() {
        return input;
    }

}
