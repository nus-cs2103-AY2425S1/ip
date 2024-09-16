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
     * This method joins all tags in the {@code Tags} object into a single string,
     * with each tag separated by a space. Tags are stored without any prefixes
     * or special characters.
     * </p>
     *
     * @return A string representation of the tags, with each tag separated by a space.
     */
    public String getDatabaseString() {
        return String.join(" ", this.tags);
    }

    /**
     * Parses a string representation of tags from the database into a {@code HashSet<String>} object.
     * <p>
     * The input string is expected to be a space-separated list of tags without any prefix
     * or special characters. Each tag is extracted from the string and added to a new {@code HashSet<String>}
     * object.
     * </p>
     *
     * @param databaseString The string containing tags separated by spaces, with no prefix.
     * @return A {@code HashSet<String>} containing the parsed tags.
     */
    public static HashSet<String> parseDatabaseString(String databaseString) {
        // Split the string by spaces and create a HashSet from the resulting array
        return new HashSet<>(Arrays.asList(databaseString.split(" ")));
    }
}
