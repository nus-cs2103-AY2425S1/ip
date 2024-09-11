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

    public String getTitle() {
        return this.tagTitle;
    }

    @Override
    public String toString() {
        return "[" + tagTitle + "]";
    }

    public String getSaveTagString() {
        return tagTitle;
    }
}
