package lemon.command;

import lemon.exception.InvalidFormatException;

/**
 * Parent abstract class for commands that takes in additional input for lemon to execute
 * @author He Yiheng
 */
public abstract class CommandWithInput extends Command {
    protected String input;

    /**
     * Constructor for CommandWithInput
     * @param ct stores the enum {@link CommandType} for troubleshooting
     * @param input input String that needs to be processed before further execution
     */
    public CommandWithInput(CommandType ct, String input) {
        super(ct);
        this.input = input;
    }

    /**
     * Process the input into different String components
     * @return String array that represent split input
     * @throws InvalidFormatException exception for missing components needed by the command
     */
    public abstract String[] processInput() throws InvalidFormatException;
}
