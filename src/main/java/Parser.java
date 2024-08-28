public class Parser {

    public CommandType parseCommand(String input) throws ArtsException {
        String[] parts = input.trim().split(" ", 2);
        String commandWord = parts[0].toUpperCase();

        try {
            return CommandType.valueOf(commandWord);
        } catch (IllegalArgumentException e) {
            throw new ArtsException("I'm sorry, but I don't know what that means.");
        }
    }

    public String[] parseArguments(String input) {
        return input.trim().split(" ", 2);
    }
}
