import java.util.Arrays;

public class Parser {

    public Command parseInputFromUser(String input) {

        // Split the input string by spaces and get the first word
        String[] splitInput = input.split(" ");
        String commandWord = splitInput[0];

        // return the rest of the input as an array of string
        int splitInputLength = splitInput.length;
        splitInput = Arrays.copyOfRange(splitInput, 1, splitInputLength);

        /*
          the following part was inspired by:
          https://stackoverflow.com/questions/2290262/search-for-a-string-in-enum-and-return-the-enum
          and minor syntax errors were debugged using ChatGPT
         */

        CommandTypes commandType;
        try {
            commandType = CommandTypes.valueOf(commandWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandTypes.UNKNOWN;
        }

        // Use the initialise method to create and return a Commands object
        return Command.initialise(commandType, splitInput);
    }

}
