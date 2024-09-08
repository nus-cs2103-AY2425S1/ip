package david.parser;

import david.exceptions.DavidInvalidArgumentsException;

/**
 * Class to parse all strings to the proper format
 */
public class StringParser extends Parser {

    /**
     * Takes an input string and returns the action/command to perform
     * @param s takes in the full input string
     * @return String denoting the action
     */
    public static String parseStringToCommand(String s) {
        String command = s.split("\s", 2)[0];
        return command.toUpperCase();
    }

    /**
     * Returns event details excluding event type
     * @param s takes in the full input string
     * @return rest of the input string excluding the first command word
     * @throws DavidInvalidArgumentsException
     */
    public static String parseStringToArguments(String s) throws DavidInvalidArgumentsException {
        String[] input = s.split("\s", 2);

        if (input.length == 1 || input[1].equals("")) {
            throw new DavidInvalidArgumentsException();
        }

        return input[1];
    }
}
