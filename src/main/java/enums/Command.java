package enums;

import exception.InvalidCommandException;

/**
 * Enum representing the different commands available.
 */
public enum Command {
    LIST("list", "list", "list all tasks"),
    TODO("todo", "todo <name>", "Add a todo with name"),
    DEADLINE("deadline", "deadline <name> /by <end date>", "Add a deadline with name and end date "),
    EVENT("event", "event <name> /from <start date> /to <end date>",
            "Add an event with name, start date and end date"),
    MARK("mark", "mark <task index>", "mark task with corresponding task index as done"),
    UNMARK("unmark", "unmark <task index>", "unmark task with corresponding task index as undone"),
    DELETE("delete", "delete <task index>", "delete task with corresponding task index"),
    FIND("find", "find <search string>", "find task with matching search string"),
    LIST_SCHEDULE("schedule", "schedule <date>", "list the schedule for a particular date"),
    HELP("help", "help", "view all available commands"),
    EXIT("bye", "bye", "exit BotManager");

    private final String input;
    private final String usage;
    private final String description;

    Command(String input, String usage, String description) {
        this.input = input;
        this.usage = usage;
        this.description = description;
    }

    public String getInput() {
        return input;
    }

    public String getUsage() {
        return usage;
    }

    /**
     * Converts an input string into the corresponding <code>Command</code>
     *
     * @param input Input string from the user.
     * @return Corresponding <code>Command</code> enum.
     * @throws InvalidCommandException If the input string does not correspond to any <code>Command</code>.
     */
    public static Command getCommandFromInput(String input) throws InvalidCommandException {
        for (Command command : Command.values()) {
            if (command.input.equals(input)) {
                return command;
            }
        }

        throw new InvalidCommandException(input);
    }

    private int getMaxUsageLength() {
        int maxUsageLenth = 0;
        for (Command command : Command.values()) {
            maxUsageLenth = Math.max(maxUsageLenth, command.usage.length());
        }
        return maxUsageLenth;
    }

    /**
     * Returns a help message based on all commands available.
     *
     * @return Help message.
     */
    public String getHelpMessage() {
        return String.format("%-" + getMaxUsageLength() + "s    %s", usage, description);
    }
}
