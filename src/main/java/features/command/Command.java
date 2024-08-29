package features.command;

public class Command {
    public String syntax;
    public String description;

    public Command(String syntax, String description) {
        this.syntax = syntax;
        this.description = description;
    }
}
