package drbrown.parsing;

import drbrown.command.Command;
import drbrown.utils.DrBrownException;

/**
 * An abstract base class for parsing user input into specific commands.
 * Subclasses are expected to implement the {@link #parse()} method to handle
 * specific command types.
 */
public abstract class Parsing {

    /** The input split into an array of strings for parsing. */
    private String[] inputSplit;

    /**
     * Constructs a {@code Parsing} instance with the given input split.
     *
     * @param inputSplit The user input split into parts for parsing.
     */
    public Parsing(String[] inputSplit) {
        this.inputSplit = inputSplit;
    }

    /**
     * Returns the input split into an array of strings.
     *
     * @return An array of strings representing the split input.
     */
    public String[] getInputSplit() {
        return this.inputSplit;
    }

    /**
     * Parses the user input into a {@link Command}.
     * This method must be implemented by subclasses to handle specific parsing logic.
     *
     * @return A {@link Command} representing the parsed user input.
     * @throws DrBrownException If there is an error during parsing.
     */
    public abstract Command parse() throws DrBrownException;

}
