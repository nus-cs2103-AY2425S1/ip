package carly.utils;

import org.apache.commons.text.similarity.LevenshteinDistance; //add gradle implementation

/** Utility class for finding the closest matching command using Levenshtein Distance. */
public class FuzzyMatch {

    /** Array of valid commands that can be input by user. **/
    private static final Command[] VALID_COMMANDS = Command.values();

    /**
     * Finds the best matching command based on the Levenshtein Distance between the user input and available commands.
     *
     * @param userInput the user's input string
     * @return the best matching Command, or null if no match is found
     */
    public static Command getBestMatch(String userInput) {
        LevenshteinDistance distanceCalculator = new LevenshteinDistance();
        Command bestMatch = null;
        int smallestDistance = Integer.MAX_VALUE;

        for (Command command : VALID_COMMANDS) {
            int distance = distanceCalculator.apply(userInput.toUpperCase(), command.toString());

            if (distance < smallestDistance) {
                smallestDistance = distance;
                bestMatch = command;
            }
        }
        assert bestMatch != null : "Best match should not be null.";
        return bestMatch;
    }

    public static void main(String[] args) {
    }

}