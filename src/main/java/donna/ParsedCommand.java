package donna;

public class ParsedCommand {
    private final String commandType;
    private final String argument1;
    private final String argument2;

    public ParsedCommand(String commandType, String argument1, String argument2) {
        this.commandType = commandType;
        this.argument1 = argument1;
        this.argument2 = argument2;
    }

    public ParsedCommand(String commandType, String argument1) {
        this(commandType, argument1, null);
    }

    public ParsedCommand(String commandType) {
        this(commandType, null, null);
    }

    public String getCommandType() {
        return commandType;
    }
    public String getArgument1() {
        return argument1;
    }

    public String getArgument2() {
        return argument2;
    }
}
