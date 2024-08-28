public class Command {
    public enum CommandType {
        List, Add, Mark, Unmark, Delete, Exit
    };

    private final String command;

    public Command(String command) {
        this.command = command;
    }

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

    public int getTaskNumber() throws InvalidCommandException {
        try {
            return Integer.parseInt(this.command.split(" ")[1]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("OOPS!!! Please enter a valid task number.");
        }
    }

    public Task.TYPE getTaskType() {
        return switch (this.command.split(" ")[0]) {
            case "todo" -> Task.TYPE.TODO;
            case "deadline" -> Task.TYPE.DEADLINE;
            case "event" -> Task.TYPE.EVENT;
            default -> Task.TYPE.UNKNOWN;
        };
    }

    public String getDescription() {
        return this.command.split("/")[0].split(" ", 2)[1].trim();
    }

    public String getDate() {
        return this.command.split("/")[1].split(" ", 2)[1].split(" ")[0].trim();
    }

    public String getStartTime() {
        return this.command.split("/")[1].split(" ", 2)[1].split(" ")[1].trim();
    }

    public String getEndTime() {
        return this.command.split("/")[2].split(" ", 2)[1].trim();
    }
}

