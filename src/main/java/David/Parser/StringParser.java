package David.Parser;

import David.Exceptions.DavidInvalidArgumentsException;

public class StringParser extends Parser{

    /*
    @param String s - takes in the full input string
    @return first word of the input string,split at every whitespace and converted to uppercase.
     */
    public static String parseStringToCommand(String s) {
        String command = s.split("\s", 2)[0];
        return command.toUpperCase();
    }

    /*
    @param String s - takes in the full input string
    @return the rest of the input string excluding the first command word
    @throws DavidUnknownActionException
     */
    public static String parseStringToArguments(String s) throws DavidInvalidArgumentsException {
        String[] input = s.split("\s", 2);
        if (input.length == 1 || input[1].equals("")) {
            throw new DavidInvalidArgumentsException();
        }
        return input[1];
    }
}
