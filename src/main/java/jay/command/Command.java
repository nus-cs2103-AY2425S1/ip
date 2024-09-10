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
        List, Add, Mark, Unmark, Delete, Find, Exit, Unknown
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
     * Returns the description from the Jay.command.
     *
     * @return The description.
     */
    public String getDescription() {
        return this.command.split("/")[0].split(" ", 2)[1].trim();
    }

    /**
     * Returns the date from the Jay.command.
     *
     * @return The date.
     */
    public String getDate() {
        return this.command.split("/")[1].split(" ", 2)[1].split(" ")[0].trim();
    }

    /**
     * Returns the start time from the Jay.command.
     *
     * @return The start time.
     */
    public String getStartTime() {
        return this.command.split("/")[1].split(" ", 2)[1].split(" ")[1].trim();
    }

    /**
     * Returns the end time from the Jay.command.
     *
     * @return The end time.
     */
    public String getEndTime() {
        return this.command.split("/")[2].split(" ", 2)[1].trim();
    }

    /**
     * Returns the keyword from the Jay.command.
     *
     * @return The keyword.
     */
    public String getKeyword() {
        return this.command.split(" ", 2)[1];
    }
}

