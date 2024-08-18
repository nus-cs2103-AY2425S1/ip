import java.util.Arrays;

public class Parser {

    public Command parseInputFromUser(String input) {

        int commandCode;

        // Split the input string by spaces and get the first word
        String[] splitInput = input.split(" ");
        String commandWord = splitInput[0];

        // return the rest of the input as an array of string
        int splitInputLength = splitInput.length;
        splitInput = Arrays.copyOfRange(splitInput, 1, splitInputLength);

        commandCode = switch (commandWord) {
            case "list" -> CommandExecutor.CommandType.LIST.ordinal();
            case "unmark" -> CommandExecutor.CommandType.UNMARK.ordinal();
            case "mark" -> CommandExecutor.CommandType.MARK.ordinal();
            case "todo" -> CommandExecutor.CommandType.TODO.ordinal();
            case "deadline" -> CommandExecutor.CommandType.DEADLINE.ordinal();
            case "event" -> CommandExecutor.CommandType.EVENT.ordinal();
            case "bye" -> CommandExecutor.CommandType.BYE.ordinal();
            default -> CommandExecutor.CommandType.UNKNOWN.ordinal();
        };

        // Use the initialise method to create and return a Commands object
        return Command.initialise(splitInput, commandCode);
    }

}
