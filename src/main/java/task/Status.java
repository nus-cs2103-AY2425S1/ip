package task;

// inspired by this stackoverflow post: https://stackoverflow.com/a/26152169
// allows for future status to be included such as RESCHEDULED(">") etc.
public enum Status {
    COMPLETE("x"),
    INCOMPLETE(" ");

    private String value;

    Status(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return this.getValue();
    }
}
