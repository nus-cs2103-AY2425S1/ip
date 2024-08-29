public class Parser {

    private String command;

    public Parser(String command) {
        this.command = command;
    }

    public static String[] divideMessage(String message) {
        return message.trim().split("\\s+");
    }

    public static String[] splitTaskInfo(String message) {
        return message.trim().split("\\s+", 2);
    }

    public Command parse(String fullCommand) {
        return new AddCommand(fullCommand);
    }
}
