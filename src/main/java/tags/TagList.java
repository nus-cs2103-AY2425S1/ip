package tags;

import java.util.HashMap;
import java.util.Set;

/**
 * Represents a list of tags.
 */
public class TagList {
    private final HashMap<String, Tag> tags;

    public TagList() {
        this.tags = new HashMap<>();
    }

    /**
     * Adds a tag object to this.tags.
     *
     */
    public void addTag(Tag tag) {
        tags.put(tag.getTagName(), tag);
    }

    /**
     * Adds a tag object to this.tags using string argument (done during loading).
     *
     * @param tagName String of name of the tag to be added.
     * @return the Tag object created.
     */
    public Tag addTagFromString(String tagName) {
        Tag tag = new Tag(tagName);
        tags.put(tagName, tag);
        return tag;
    }

    /**
     * Removes a tag from this.tags.
     *
     * @param tagName the name of the tag to be removed.
     */
    public void removeTag(String tagName) {
        tags.remove(tagName);
    }

    /**
     * Checks if a tag is in this.tags.
     *
     * @param tagName the name of the tag to be checked.
     * @return true if tag is in list, false otherwise.
     */
    public boolean containsTag(String tagName) {
        return tags.containsKey(tagName);
    }

    /**
     * Gets a tag object from this.tags.
     *
     * @param tagName the name of the tag to be retrieved.
     * @return the tag object.
     */
    public Tag getTag(String tagName) {
        return tags.get(tagName);
    }

    /**
     * Gets set of all tag names in the list.
     *
     * @return Set of all String representation of tag names.
     */
    public Set<String> getAllTags() {

        return tags.keySet();
    }


}
