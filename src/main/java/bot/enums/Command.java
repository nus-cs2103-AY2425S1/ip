package bot.enums;

import bot.exceptions.InvalidCommandException;

/**
 * Represents a command issued by the user.
 */
public enum Command {
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    FIND("find"),
    UNDO("undo"),
    EXIT("bye");

    public final String name;

    /**
     * Creates a new <code>Command</code> object.
     *
     * @param name of the command.
     */
    Command(String name) {
        this.name = name;
    }

    /**
     * Converts a string into a <code>Command</code>.
     *
     * @param input string representing a command.
     * @return the corresponding <code>Command</code>.
     * @throws InvalidCommandException if the given command string does not match any enum.
     */
    public static Command fromString(String input) throws InvalidCommandException {
        for (Command cmd : Command.values()) {
            if (cmd.name.equals(input)) {
                return cmd;
            }
        }
        throw new InvalidCommandException(input);
    }
}
