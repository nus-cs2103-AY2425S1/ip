package bot.enums;

import bot.exceptions.InvalidCommandException;

public enum Command {
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    BYE("bye"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    public final String name;

    Command(String name) {
        this.name = name;
    }

    public static Command fromString(String input) throws InvalidCommandException {
        for (Command cmd : Command.values()) {
            if (cmd.name.equals(input)) {
                return cmd;
            }
        }
        throw new InvalidCommandException(input);
    }
}
