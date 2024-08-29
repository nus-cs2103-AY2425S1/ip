package command;

import task.Task;

/**
 * Represents a command that the user inputs.
 */
public class Command {
    public enum CommandType {
        List, Add, Mark, Unmark, Delete, Exit
    };

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

        return switch (commandType) {
            case "list" -> CommandType.List;
            case "bye" -> CommandType.Exit;
            case "mark" -> CommandType.Mark;
            case "unmark" -> CommandType.Unmark;
            case "delete" -> CommandType.Delete;
            default -> CommandType.Add;
        };
    }

    /**
     * Returns the task number from the command.
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
     * Returns the task type from the command.
     *
     * @return The task type.
     */
    public Task.TYPE getTaskType() {
        return switch (this.command.split(" ")[0]) {
            case "todo" -> Task.TYPE.TODO;
            case "deadline" -> Task.TYPE.DEADLINE;
            case "event" -> Task.TYPE.EVENT;
            default -> Task.TYPE.UNKNOWN;
        };
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
}

