package torne.command.argument;

import torne.command.Command;

/**
 * Class that encapsulates an arguments that a {@link Command} accepts.
 * <br>
 * This is necessary so that additional information about a particular argument: its quick description and detailed
 * description can be given.
 */
public class CommandArgument {
    protected String name;
    protected String descriptionShort;
    protected String descriptionDetailed;

    /**
     * Creates a {@link CommandArgument}.
     *
     * @param name                Name of the argument - this argument is called by a `/[name]` flag
     * @param descriptionShort    Short description of the argument. Typically, the format expected as a value.
     * @param descriptionDetailed Long prose description of the argument. May include examples of types of values.
     */
    public CommandArgument(String name, String descriptionShort, String descriptionDetailed) {
        this.name = name;
        this.descriptionShort = descriptionShort;
        this.descriptionDetailed = descriptionDetailed;
    }

    public String getName() {
        return name;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public String getDescriptionDetailed() {
        return descriptionDetailed;
    }
}

