package task;

/**
 * Represents a tag associated with a task.
 * Each tag has a unique name that is used to categorize or label tasks.
 */
public class Tag {

    /** The name of the tag. */
    private final String tagName;

    /**
     * Constructs a {@code Tag} with the specified tag name.
     *
     * @param tagName The name of the tag, typically used to label or categorize tasks.
     */
    public Tag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Returns the name of the tag.
     *
     * @return The name of the tag as a {@code String}.
     */
    @Override
    public String toString() {
        return tagName;
    }
}
