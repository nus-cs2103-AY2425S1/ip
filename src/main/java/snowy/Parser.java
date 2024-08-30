package snowy;

/**
 * Represents a parser that helps to parse and understand user input.
 */
public class Parser {


    /**
     * Parses and seperates the user input into command and description.
     * @param input the input of the user.
     * @return an array with the first element as the command in lowercase, and the second element as the descriptions.
     */
    public static String[] parse(String input) {
        int spaceIndex = input.indexOf(" ");

        String command = (spaceIndex == -1 ? input: input.substring(0, spaceIndex)).toLowerCase();

        String description = spaceIndex == -1 ? "": input.substring(spaceIndex + 1);

        return new String[] {command, description};
    }
}
