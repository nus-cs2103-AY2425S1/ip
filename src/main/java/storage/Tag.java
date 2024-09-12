package storage;

/**
 * Represents a tag for a task.
 */
public class Tag {
    private final String name;

    /**
     * Creates a new Tag.
     *
     * @param name The name of the tag.
     */
    public Tag(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the tag.
     *
     * @return The name of the tag.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "#" + name;
    }
}
