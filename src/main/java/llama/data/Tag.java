package llama.data;

/**
 * Represents a tag for a given task
 */
public class Tag {
    private String tagTitle;

    /**
     * Creates a tag that can be tagged to a task
     *
     * @param tagTitle Title of tag
     */
    public Tag(String tagTitle) {
        this.tagTitle = tagTitle.toLowerCase();
    }

    /**
     * Returns the title of the tag
     *
     * @return Title of tag
     */
    public String getTitle() {
        return this.tagTitle;
    }

    @Override
    public String toString() {
        return "[" + tagTitle + "]";
    }

    /**
     * Returns the tag in a format that can be saved to a file
     *
     * @return Tag in a format that can be saved to a file
     */
    public String getSaveTagString() {
        return tagTitle;
    }
}
