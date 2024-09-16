package secondmind;

/**
 * Processes user input and parses it into executable commands.
 */
public class Parser {
    /**
     * Constructor for the Parser class.
     */
    public Parser() {}

    /**
     * Processes the user's input string and returns the corresponding command and arguments.
     *
     * @param input The input string from the user.
     * @return A string array representing the command and its arguments.
     * @throws NumberFormatException If the input contains an invalid number format.
     */
    public String[] processInput(String input) throws NumberFormatException {
        String[] newInput = input.split(" ");
        String[] ignoreInstruction = new String[] {"ignore"};
        String command = newInput[0];
        if (command.equals("bye") && newInput.length == 1) {
            return new String[] {"bye"};
        } else if (command.equals("mark")) {
            try {
                int taskNumber = Integer.parseInt(newInput[1]);
                return new String[] {"mark", String.valueOf(taskNumber)};
            } catch (NumberFormatException e) {
                throw e;
            } 
        } else if (command.equals("unmark")) {
            try {
                int taskNumber = Integer.parseInt(newInput[1]);
                return new String[] {"unmark", String.valueOf(taskNumber)};
            } catch (NumberFormatException e) {
                throw e;
            } 
        } else if (command.equals("delete")) {
            try {
                int taskNumber = Integer.parseInt(newInput[1]);
                return new String[] {"delete", String.valueOf(taskNumber)};
            } catch (NumberFormatException e) {
                throw e;
            } 
        } else if (command.equals("list")) {
            return new String[] {"list"};
        } else if (command.equals("find")) {
            return newInput;
        } else {
            return new String[] {"add", input};
        }
    }
}