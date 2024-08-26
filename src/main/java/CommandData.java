public class CommandData {
    private final String commandType;
    private final String rawInput;

    public CommandData(String commandType, String rawInput) {
        this.commandType = commandType;
        this.rawInput = rawInput;
    }

    public String getCommandType() {
        return commandType;
    }

    public String getRawInput() {
        return rawInput;
    }
}