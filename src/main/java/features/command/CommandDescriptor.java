package features.command;

public class CommandDescriptor {
    public String syntax;
    public String description;

    public CommandDescriptor(String syntax, String description) {
        this.syntax = syntax;
        this.description = description;
    }
}
