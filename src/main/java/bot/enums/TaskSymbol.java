package bot.enums;

import bot.exceptions.InvalidTaskEnumException;

public enum TaskSymbol {
    TODO("T"),
    EVENT("E"),
    DEADLINE("D");

    public final String name;

    TaskSymbol(String name) {
        this.name = name;
    }

    public static TaskSymbol fromString(String input) throws InvalidTaskEnumException {
        for (TaskSymbol cmd : TaskSymbol.values()) {
            if (cmd.name.equals(input)) {
                return cmd;
            }
        }
        throw new InvalidTaskEnumException(input);
    }
}
