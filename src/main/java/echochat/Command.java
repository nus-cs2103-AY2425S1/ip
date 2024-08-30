package echochat;

public class Command {


    private CommandType type;
    private String description;
    private int index;
    private Task task;

    public Command(CommandType type, String description, int index) {
        this.type = type;
        this.description = description;
        this.index = index;
    }

    public Command(CommandType type, String description, int index, Task task) {
        this.type = type;
        this.description = description;
        this.index = index;
        this.task = task;
    }

    /**
     * Returns type of command.
     * @return CommandType enum
     */
    public CommandType getType() {
        return type;
    }

    /**
     * Returns command index,
     * @return Integer representing command index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns task of the command.
     * @return Task
     */
    public Task getTask() {
        return task;
    }

    public String getDescription() {
        return description;
    }
}
