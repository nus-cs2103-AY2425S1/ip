public class Event extends Task {
    private String at;
    private String to;

    public Event(String description, String at, String to) {
        super(description);
        this.at = at;
        this.to = to;
    }

    public String toSaveString() {
        return "E" + Barney.SAVE_FILE_DELIMITER + super.toSaveString() + Barney.SAVE_FILE_DELIMITER + at
                + Barney.SAVE_FILE_DELIMITER + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + " to: " + to + ")";
    }
}
