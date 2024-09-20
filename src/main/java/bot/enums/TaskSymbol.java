package bot.enums;

import bot.exceptions.InvalidTaskEnumException;

/**
 * Represents the symbol of <code>Task</code> objects.
 *
 * @author mongj
 */
public enum TaskSymbol {
    TODO("T"),
    EVENT("E"),
    DEADLINE("D");

    public final String name;

    /**
     * Creates a new <code>TaskSymbol</code> object.
     *
     * @param name of the <code>TaskSymbol</code>, i.e. the symbol used to represent the task.
     */
    TaskSymbol(String name) {
        this.name = name;
    }

    /**
     * Converts a string into a <code>TaskSymbol</code>.
     *
     * @param input string representing a symbol.
     * @return the corresponding <code>TaskSymbol</code>.
     * @throws InvalidTaskEnumException if the given string does not match any enum.
     */
    public static TaskSymbol fromString(String input) throws InvalidTaskEnumException {
        for (TaskSymbol cmd : TaskSymbol.values()) {
            if (cmd.name.equals(input)) {
                return cmd;
            }
        }
        throw new InvalidTaskEnumException(input);
    }
}
