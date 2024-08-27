public class Parser {

    /**
     * Parses the user input into a command and its arguments.
     *
     * @param input the raw input string from the user
     * @return an array where the first element is the command and the second element is the arguments (if any)
     */
    public static String[] parse(String input) {
        String[] result = new String[2];
        String[] splitInput = input.trim().split(" ", 2);
        result[0] = splitInput[0];
        result[1] = splitInput.length > 1 ? splitInput[1] : "";
        return result;
    }
}
