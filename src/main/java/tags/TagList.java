package tags;

import java.util.HashMap;
import java.util.Set;

/**
 * Represents a list of tags
 */
public class TagList {
    private final HashMap<String, Tag> tags;

    public TagList() {
        this.tags = new HashMap<>();
    }

    /**
     * adds a tag object to list of tags
     *
     */
    public void addTag(Tag tag) {
        tags.put(tag.getTagName(), tag);
    }

    /**
     * adds a tag object to list of tags using string argument (done during loading)
     *
     * @param tagName the name of the tag to be added
     * @return the tag object created
     */
    public Tag addTagFromString(String tagName) {
        Tag tag = new Tag(tagName);
        tags.put(tagName, tag);
        return tag;
    }

    /**
     * removes a tag from the list of tags
     *
     * @param tagName the name of the tag to be removed
     */
    public void removeTag(String tagName) {
        tags.remove(tagName);
    }

    /**
     * checks if a tag is in the list of tags
     *
     * @param tagName the name of the tag to be checked
     * @return true if tag is in list, false otherwise
     */
    public boolean containsTag(String tagName) {
        return tags.containsKey(tagName);
    }

    /**
     * gets a tag object from the list of tags
     *
     * @param tagName the name of the tag to be retrieved
     * @return the tag object
     */
    public Tag getTag(String tagName) {
        return tags.get(tagName);
    }

    /**
     * gets the list of tags
     *
     * @return the String with all tags
     */
    public Set<String> getAllTags() {

        return tags.keySet();
    }


}
