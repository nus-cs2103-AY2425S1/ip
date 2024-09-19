package task.tags;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Tags {
    private final HashSet<String> tags;

    /**
     * Constructs a Tags instance with an empty tag set.
     */
    public Tags() {
        this.tags = new HashSet<>();
    }

    /**
     * Constructs a Tags instance with the given collection of tags.
     *
     * @param tags The collection of tags to initialize the tag set with.
     */
    public Tags(Collection<String> tags) {
        this.tags = new HashSet<>(tags);
    }

    /**
     * Checks if the tag set is empty.
     *
     * @return true if the tag set is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.tags.isEmpty();
    }

    /**
     * Returns a string representation of the tags with a "#" prefix for each tag.
     * <p>
     * If the set of tags is empty, this method returns an empty string. If there are tags,
     * each tag is prefixed with "#" and the tags are joined by a space. The result is enclosed
     * in square brackets.
     * </p>
     *
     * @return A string representation of the tags, with each tag prefixed by "#" and separated
     * by a space, enclosed in square brackets. Returns an empty string if there are no tags.
     */
    @Override
    public String toString() {
        return this.isEmpty()
                ? ""
                : String.format(
                "[%s]",
                this.tags.stream()
                        .map(tag -> "#" + tag)
                        .collect(Collectors.joining(" "))
        );
    }

    /**
     * Returns a string representation of the tags suitable for database storage.
     * <p>
     * The tags are encapsulated within square brackets, and each tag is separated by a comma.
     * If there are no tags, the method returns empty square brackets.
     * </p>
     *
     * @return A string representation of the tags, encapsulated within square brackets and separated by commas.
     */
    public String getDatabaseString() {
        return "[" + String.join(",", this.tags) + "]";
    }

    /**
     * Parses a string representation of tags from the database into a {@code HashSet<String>} object.
     * <p>
     * The input string is expected to be encapsulated in square brackets with tags separated by commas.
     * The method removes the square brackets, splits the tags by commas, and returns them in a {@code HashSet<String>}.
     * If no tags are present, an empty {@code HashSet<String>} is returned.
     * </p>
     *
     * @param databaseString The string containing tags separated by commas, encapsulated in square brackets.
     * @return A {@code HashSet<String>} containing the parsed tags, or an empty set if no tags are present.
     */
    public static HashSet<String> parseDatabaseString(String databaseString) {
        // Remove the square brackets and split by commas
        String cleanedString = databaseString.substring(1, databaseString.length() - 1);
        // If the string is empty, return an empty HashSet
        if (cleanedString.isEmpty()) {
            return new HashSet<>();
        }
        return new HashSet<>(Arrays.asList(cleanedString.split(",")));
    }

}
