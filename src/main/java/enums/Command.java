package enums;

import exception.InvalidCommandException;

public enum Command {
    LIST("list", "list", "list all tasks"),
    TODO("todo", "todo <name>", "Add a todo with name"),
    DEADLINE("deadline", "deadline <name> /by <end date>", "Add a deadline with name and end date " ),
    EVENT("event", "event <name> /from <start date> /end <end date>",
            "Add an event with name, start date and end date"),
    MARK("mark", "mark <task index>", "mark task with corresponding task index as done"),
    UNMARK("unmark", "unmark <task index>", "unmark task with corresponding task index as undone"),
    DELETE("delete", "delete <task index>", "delete task with corresponding task index"),
    HELP("help", "help", "show information on all commands"),
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

    public String getDescription() {
        return description;
    }

    public static Command getCommandFromInput(String input) throws InvalidCommandException {
        for (Command command : Command.values()) {
            if (command.getInput().equals(input)) {
                return command;
            }
        }

        throw new InvalidCommandException(input);
    }
}
