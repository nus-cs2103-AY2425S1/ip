import java.util.Arrays;

public class Parser {

    public static Command parseInputFromUser(String input) {

        // Trim and split the input into designated parts
        String[] splitInput = input.trim().replaceAll("\\s+", " ").split(" ");
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

    // The following code was generated with ChatGPT with minor edits.
    public static Task parseInputFromFile(String input) {

        String[] parts = input.split("\\|");
        String taskType = parts[0];  // The first letter indicating the task type
        boolean isDone = Boolean.parseBoolean(parts[1].trim());
        String description = parts[2].trim();

        // Create task object based on taskType
        switch (taskType) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            String deadline = parts[3].trim();
            return new Deadline(description, deadline, isDone);
        case "E":
            String fromTime = parts[3].trim();
            String toTime = parts[4].trim();
            return new Events(description, fromTime, toTime, isDone);
        default:
            FormattedPrinting.fileCorrupted();
            return null;
        }
    }

}
