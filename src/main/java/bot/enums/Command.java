package bot.enums;

import bot.exceptions.InvalidCommandException;

/**
 * Represents a command issued by the user.
 */
public enum Command {
    LIST("list", "list", "List all tasks"),
    MARK("mark", "mark <index>", "Mark a task as completed"),
    UNMARK("unmark", "unmark <index>", "Mark as task as incomplete"),
    DELETE("delete", "delete <index>", "Delete a task from the task list"),
    TODO("todo", "todo <task>", "Adds a new todo to the task list"),
    DEADLINE("deadline", "deadline <task> /by <date>", "Adds a new deadline to the task list"),
    EVENT("event", "event <task> /from <date> /to <date>", "Adds a new event to the task list"),
    FIND("find", "find <query>", "Filters the task list by the queried term"),
    UNDO("undo", "undo", "Reverts the last executed command"),
    HELP("help", "help", "Displays a list of available commands and their usages"),
    EXIT("bye", "bye", "Ends the chat and closes the app");

    private final String name;
    private final String usage;
    private final String description;

    /**
     * Creates a new <code>Command</code> object.
     *
     * @param name of the command.
     */
    Command(String name, String usage, String description) {
        this.name = name;
        this.usage = usage;
        this.description = description;
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

    public String getHelpMessage() {
        return "• " + name + ": " + usage + "\n " + description;
    }
}
