package torne.command.argument;

/**
 * Specific type of command argument that is a default argument. i.e. the main argument supplied to a command,
 * without a flag.
 */
public class DefaultCommandArgument extends CommandArgument {

    /**
     * Creates a {@link DefaultCommandArgument} - a non-flagged command argument.
     *
     * @param descriptionShort    Short description of the argument. Typically, the format expected as a value.
     * @param descriptionDetailed Long prose description of the argument. May include examples of types of values.
     */
    protected DefaultCommandArgument(String descriptionShort, String descriptionDetailed) {
        super("", descriptionShort, descriptionDetailed);
    }
}
