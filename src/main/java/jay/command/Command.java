package jay.command;

import jay.task.Task;

/**
 * Represents a Jay.command that the user inputs.
 */
public class Command {
    /**
     * Represents the type of Jay.command.
     */
    public enum CommandType {
        List, Add, Mark, Unmark, Delete, Find, Exit, Unknown
    };

    private final String command;

    public Command(String command) {
        this.command = command;
    }

    // CHECKSTYLE.OFF: Indentation
    /**
     * Returns the type of Jay.command that user input.
     *
     * @return The type of Jay.command.
     */
    public CommandType getCommandType() {
        String commandType = this.command.split(" ")[0];

        return switch (commandType) {
            case "list" -> CommandType.List;
            case "bye" -> CommandType.Exit;
            case "mark" -> CommandType.Mark;
            case "unmark" -> CommandType.Unmark;
            case "delete" -> CommandType.Delete;
            case "find" -> CommandType.Find;
            case "todo", "deadline", "event" -> CommandType.Add;
            default -> CommandType.Unknown;
        };
    }

    // CHECKSTYLE.ON: Indentation
    /**
     * Returns the Jay.task number from the Jay.command.
     *
     * @return The Jay.task number.
     * @throws InvalidCommandException If the Jay.command is invalid.
     */
    public int getTaskNumber() throws InvalidCommandException {
        try {
            return Integer.parseInt(this.command.split(" ")[1]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("OOPS!!! Please enter a valid task number.");
        }
    }

    //CHECKSTYLE.OFF: Indentation
    /**
     * Returns the Jay.task type from the Jay.command.
     *
     * @return The Jay.task type.
     */
    public Task.Type getTaskType() {
        return switch (this.command.split(" ")[0]) {
            case "todo" -> Task.Type.ToDo;
            case "deadline" -> Task.Type.Deadline;
            case "event" -> Task.Type.Event;
            default -> Task.Type.Unknown;
        };
    }
    // Checkstyle.ON: Indentation

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

