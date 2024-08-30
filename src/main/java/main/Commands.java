package main;

import exception.CommandNotFoundException;

public enum Commands {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    BYE("bye"),
    FIND("find");

    private final String value;
    Commands(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    /**
     * Returns the corresponding Commands enum value specified by the String input.
     * If the String input does not match any values, a CommandNotFoundException is thrown.
     *
     * @param command the String input by the user to match against Commands enum values
     * @return the Commands enum value that matches the input string
     * @throws CommandNotFoundException if the input string does not match any Commands enum value
     */
    public static Commands fromString(String command) throws CommandNotFoundException {
        for (Commands cmd : Commands.values()) {
            if (cmd.getValue().equalsIgnoreCase(command)) {
                return cmd;
            }
        }
        throw new CommandNotFoundException(command);
    }
}
