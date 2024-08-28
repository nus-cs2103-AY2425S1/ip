package bibi;

public class Parser {
    public static Command parseCommand(String input) {
        String cmd = input.split(" ")[0];
        return new Command(cmd, input.substring(cmd.length()).trim());
    }
}
