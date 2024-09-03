package casper;

import java.util.HashMap;

/**
 * Represents the Parser class
 */
public class Parser {
    public Parser() {}

    /**
     * Splits the given string into an array of 2 strings, separated by the first space
     * @return String[] of length 2, if input has no space, the second string is the empty string
     */
    public String[] splitInputIntoTwo(String input) {
        int firstSpaceIndex = input.indexOf(" ");

        // If there is no space, return the original string as the first element
        if (firstSpaceIndex == -1) {
            return new String[] { input, "" };
        }

        // Split the string into two parts
        String part1 = input.substring(0, firstSpaceIndex).trim(); // Before the first space
        String part2 = input.substring(firstSpaceIndex + 1).trim(); // After the first space

        return new String[] { part1, part2 };
    }

    /**
     * Parses the given input by slashes, storing them as key-value pairs in the hashmap
     * For each parsed input, the input is further split into 2, separated by the first space.
     * The first string is the key, and the second string is the value.
     */
    public void parseBySlash(String input, HashMap<String, String> hashMap) {
        String[] parsedInput = input.split("/");
        hashMap.put("description", parsedInput[0].trim());
        for (int i = 1; i < parsedInput.length; i++) {
            String[] inputPart = splitInputIntoTwo(parsedInput[i]);
            hashMap.put(inputPart[0].trim(), inputPart[1].trim());
        }
    }
}
