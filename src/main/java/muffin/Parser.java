package muffin;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Responsible for parsing user input into distinct components.
 * It splits the input string into a command and its respective arguments, handling
 * cases where the input includes additional parameters separated by slashes ("/").
 */
public class Parser {

    /**
     * Parses the user input into an array of strings, where the first element is the command
     * and the subsequent elements are the command's arguments.
     *
     * @param input The user input string to be parsed.
     * @return An array of strings where the first element is the command, and the following elements are the arguments.
     */
    public String[] parseInput(String input) {
        // Find the index of the first space
        int firstSpaceIndex = input.indexOf(" ");

        // If a space is found, split the first word
        String firstWord = input;
        String remainingString = "";
        if (firstSpaceIndex != -1) {
            firstWord = input.substring(0, firstSpaceIndex);
            remainingString = input.substring(firstSpaceIndex + 1);
        }

        String[] parts = remainingString.split("/");
        for (int i = 1; i < parts.length; i++) {
            // Trim to remove any leading/trailing spaces
            parts[i] = parts[i].trim();

            // Remove the first word in each split part
            int spaceIndex = parts[i].indexOf(" ");
            if (spaceIndex != -1) {
                parts[i] = parts[i].substring(spaceIndex + 1).trim();
            }
        }

        return Stream.concat(
                Stream.of(firstWord),
                Arrays.stream(parts)
        ).toArray(String[]::new);
    }
}
