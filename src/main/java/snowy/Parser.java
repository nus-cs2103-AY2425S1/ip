package snowy;

public class Parser {

    public static String[] parse(String input) {
        int spaceIndex = input.indexOf(" ");

        String command = (spaceIndex == -1 ? input: input.substring(0, spaceIndex)).toLowerCase();

        String description = spaceIndex == -1 ? "": input.substring(spaceIndex + 1);

        return new String[] {command, description};
    }
}
