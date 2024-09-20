package cloud.command;


/**
 * Represents the types of commands available.
 * Each command type has a usage string and description.
 */
public enum CommandType {
    LIST("list", "Shows all tasks in your list"),
    TODO("todo <description>", "Adds a todo task"),
    DEADLINE("deadline <description> /by <dd/MM/yyyy HH:mm>", "Adds a deadline task"),
    EVENT("event <description> /from <dd/MM/yyyy HH:mm> /to <dd/MM/yyyy HH:mm>", "Adds an event task"),
    DELETE("delete <task number>", "Deletes a task"),
    MARK("mark <task number>", "Marks a task as done"),
    UNMARK("unmark <task number>", "Marks a task as not done"),
    FIND("find <keyword>", "Finds tasks that match the keyword"),
    HELP("help", "Shows the list of all available commands"),
    EXIT("bye", "Exits the application");

    private final String usage;
    private final String description;

    CommandType(String usage, String description) {
        this.usage = usage;
        this.description = description;
    }

    public String toString() {
        return this.usage + " --- " + this.description;
    }
}
