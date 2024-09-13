package task;

public class Tag {

    private final String tagName;

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return tagName;
    }
}
