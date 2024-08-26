package main.java.echochat;

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

    public CommandType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getIndex() {
        return index;
    }

    public Task createTask() {
        return task;
    }
}
