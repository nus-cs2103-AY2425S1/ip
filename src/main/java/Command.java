public class Command {
    public static boolean checkCommand(String input, String command) {
        // use for commands that require additional arguments
        return input.split(" ")[0].equalsIgnoreCase(command);
    }

    public static boolean checkEqualCommand(String input, String command) {
        // use for commands that do not require additional arguments
        return input.equalsIgnoreCase(command);
    }
}
