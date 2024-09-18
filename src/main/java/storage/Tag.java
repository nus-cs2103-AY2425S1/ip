package storage;

import skibidi.SkibidiException;

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
    public Tag(String name) throws SkibidiException {
        if (name.trim() == "") {
            throw new SkibidiException("Tag name cannot be empty.");
        }
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
    /**
     * Returns true if the tag has the same name as the other tag.
     *
     * @param obj The other object to compare to.
     * @return True if the tag has the same name as the other tag.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Tag)) {
            return false;
        }
        Tag otherTag = (Tag) obj;
        return name.equals(otherTag.name);
    }
}
