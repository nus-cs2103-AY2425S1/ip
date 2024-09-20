package Johnson.utils;

/**
 * Represents a tag that can be attached to a task.
 */
public class Tag {
    /**
     * The tag name.
     */
    private final String tag;

    public Tag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return tag;
    }
}
