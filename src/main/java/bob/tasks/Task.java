package bob.tasks;

import java.util.Arrays;

/**
 * Task is an abstract class
 * It stores the task name and the status of the task
 */
public abstract class Task {
    private final String taskName;
    private boolean isCompleted;

    /**
     * Constructor for Task
     * Sets the status of isCompleted to be false by default
     *
     * @param taskName The name of the task
     */
    public Task(String taskName) {
        assert taskName.charAt(0) != ' ';
        assert taskName.charAt(taskName.length() - 1) != ' ';
        this.taskName = taskName;
        this.isCompleted = false;
    }

    /**
     * Sets the status of isCompleted to be true to indicate that the task has been completed
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Sets the status of isCompleted to be false to indicate that the task has not been completed.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Checks if the given string is contained in the Task Name
     * Returns true if it is contained and false if it is not
     *
     * @param searchString String to search for in the task name
     * @return If the string is in the task name
     */
    public boolean contains(String searchString) {
        return this.taskName.toUpperCase().contains(searchString.toUpperCase());
    }

    /**
     * Checks if the given string has a fuzzy match in the Task Name with a 60% similarity
     * Returns true if there is a fuzzy match and false if there isn't
     *
     * @param searchString String to search for in the task name
     * @return If the string is in the task name
     */
    public boolean fuzzyContains(String searchString) {
        if (searchString.length() > this.taskName.length()) {
            return LevenshteinDistanceForSubstrings(searchString.toLowerCase(), this.taskName.toLowerCase()) < searchString.length() * 0.4;
        }
        return LevenshteinDistanceForSubstrings(this.taskName.toLowerCase(), searchString.toLowerCase()) < searchString.length() * 0.4;
    }

    /**
     * Returns the smallest number of changes to be made between sub-strings of the original and the matchStr
     * using Levenshtein Distance Algorithm. Uses sub-strings to match so that insertions and deletions are not
     * counted as dissimilar.
     *
     * @param original Main string to be sub-stringed to check for fuzzy matches
     * @param matchStr String to be fuzzy matched for in the original
     * @return smallest number of changes to be made between sub-strings of the original and the matchStr
     */
    private int LevenshteinDistanceForSubstrings(String original, String matchStr) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < original.length() - matchStr.length(); i++) {
            int curr = LevenshteinDistance(original.substring(i, i + matchStr.length()), matchStr);
            if (curr < min) {
                min = curr;
            }
        }
        return min;
    }

    /**
     * Calculates the dissimilarity between two strings using Levenshtein Distance Algorithm
     * Solution below adapted from https://www.baeldung.com/java-levenshtein-distance
     *
     * @param x String to compare
     * @param y String to compare
     * @return number of changes to be made to transform x into y
     */
    private int LevenshteinDistance(String x, String y) {
        int[][] dp = new int[x.length() + 1][y.length() + 1];

        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int curr = dp[i - 1][j - 1] + (x.charAt(i - 1) == y.charAt(j - 1) ? 0 : 1);
                    dp[i][j] = Math.min(curr, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }

        return dp[x.length()][y.length()];
    }

    /**
     * Exports the Task object to string to be saved in a text file
     *
     * @return String format of the Task object to be exported
     */
    public String export() {
        return String.format("%s %s", this.isCompleted, this.taskName);
    }

    /**
     * Converts the Task object to a string to be printed
     *
     * @return String format of the Task object for printing
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isCompleted ? "X" : " ", this.taskName);
    }
}
