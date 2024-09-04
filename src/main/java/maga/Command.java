package maga;

public class Command<T> {
    private final String commandType;
    private String description = "";
    private final T content;

    public Command(String command, T content) {
        this.commandType = command;
        this.content = content;
    }

    public Command(String command, String description, T content) {
        this.commandType = command;
        this.description = description;
        this.content = content;
    }

    public String getCommandType() {
        return commandType;
    }

    public T getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }
}
