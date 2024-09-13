package jay.command;

import jay.task.Task;

/**
 * Represents a command that the user inputs.
 */
public class Command {
    /**
     * Represents the type of command.
     */
    public enum CommandType {
        List, Add, Mark, Unmark, Delete, Find, Exit, Set, Help, Unknown
    }

    private final String command;

    public Command(String command) {
        this.command = command;
    }

    /**
     * Returns the type of command that user input.
     *
     * @return The type of command.
     */
    public CommandType getCommandType() {
        String commandType = this.command.split(" ")[0];

        switch (commandType) {
        case "list":
            return CommandType.List;
        case "bye":
            return CommandType.Exit;
        case "mark":
            return CommandType.Mark;
        case "unmark":
            return CommandType.Unmark;
        case "delete":
            return CommandType.Delete;
        case "find":
            return CommandType.Find;
        case "todo", "deadline", "event":
            return CommandType.Add;
        case "set":
            return CommandType.Set;
        case "help":
            return CommandType.Help;
        default:
            return CommandType.Unknown;
        }
    }

    /**
     * Returns the task number from the Jay.command.
     *
     * @return The task number.
     * @throws InvalidCommandException If the command is invalid.
     */
    public int getTaskNumber() throws InvalidCommandException {
        try {
            return Integer.parseInt(this.command.split(" ")[1]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("OOPS!!! Please enter a valid task number.");
        }
    }

    /**
     * Returns the task type from the Jay.command.
     *
     * @return The task type.
     */
    public Task.Type getTaskType() {
        switch (this.command.split(" ")[0]) {
        case "todo":
            return Task.Type.ToDo;
        case "deadline":
            return Task.Type.Deadline;
        case "event":
            return Task.Type.Event;
        default:
            return Task.Type.Unknown;
        }
    }

    /**
     * Returns the description from the command.
     *
     * @return The description.
     */
    public String getDescription() {
        return this.command.split("/")[0].split(" ", 2)[1].trim();
    }

    /**
     * Returns the date from the command.
     *
     * @return The date.
     */
    public String getDate() {
        return this.command.split("/")[1].split(" ", 2)[1].split(" ")[0].trim();
    }

    /**
     * Returns the start time from the command.
     *
     * @return The start time.
     */
    public String getStartTime() {
        return this.command.split("/")[1].split(" ", 2)[1].split(" ")[1].trim();
    }

    /**
     * Returns the end time from the command.
     *
     * @return The end time.
     */
    public String getEndTime() {
        return this.command.split("/")[2].split(" ", 2)[1].trim();
    }

    /**
     * Returns the keyword from the command.
     *
     * @return The keyword.
     */
    public String getKeyword() {
        return this.command.split(" ", 2)[1];
    }

    /**
     * Returns the priority from the command.
     *
     * @return The priority.
     */
    public Task.Priority getPriority() {
        String priority = this.command.split(" ", 3)[2];
        switch (priority.toLowerCase()) {
        case "high":
            return Task.Priority.High;
        case "medium":
            return Task.Priority.Medium;
        case "low":
            return Task.Priority.Low;
        default:
            return Task.Priority.Unknown;
        }
    }

    /**
     * Returns the help message.
     *
     * @return The help message.
     */
    public String getHelp() throws InvalidCommandException {
        String generalMessage = """
                Here are the commands you can use:
                1. todo - Adds a todo task.
                2. deadline  - Adds a deadline task.
                3. event  - Adds an event task.
                4. list - Shows the tasks in the list.
                5. mark - Marks a task as done.
                6. unmark - Marks a task as not done.
                7. delete - Deletes a task.
                8. find - Finds tasks that contain the keyword.
                9. set - Sets the priority of a task.
                10. bye - Exits the program.
               \s
                find out the usage of the commands by typing help /<command>
           \s""";

        String[] helpCommand = this.command.split("/", 2);

        if (helpCommand.length == 1) {
            return generalMessage;
        } else {
            return this.getSpecificHelp(helpCommand[1]);
        }
    }

    private String getSpecificHelp(String command) throws InvalidCommandException {
        String message = "";

        switch (command.trim()) {
        case "todo":
            message = """
                    todo <description> - Adds a todo task.
                    Example: todo read book
                    
                    """;
            break;
        case "deadline":
            message = """
                    deadline <description> /by <date> - Adds a deadline task.
                    Example: deadline return book /by 28-07-2024
                    
                    """;
            break;
        case "event":
            message = """
                    event <description> /from <date> <start time> /to <end time> - Adds an event task.
                    Example: event project meeting /from 30-07-2024 1400 /to 1600
                    
                    """;
            break;
        case "list":
            message = "list - Shows the tasks in the list.\n\n";
            break;
        case "mark":
            message = """
                    mark <task number> - Marks a task as done.
                    Example: mark 1
                    
                    """;
            break;
        case "unmark":
            message = """
                    unmark <task number> - Marks a task as not done.
                    Example: unmark 1
                    
                    """;
            break;
        case "delete":
            message = """
                    delete <task number> - Deletes a task.
                    Example: delete 1
                    
                    """;
            break;
        case "find":
            message = """
                    find <keyword> - Finds tasks that contain the keyword.
                    Example: find book
                    
                    """;
            break;
        case "set":
            message = """
                    set <task number> <priority> - Sets the priority of a task.
                    Example: set 1 high
                    
                    """;
            break;
        case "bye":
            message = "bye - Exits the program.\n\n";
            break;
        default:
            throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return message + "Can find out more usage about the commands by following the examples.";
    }
}

