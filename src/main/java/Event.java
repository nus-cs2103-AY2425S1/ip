public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getTaskType() {
        return "[E]";
    }

    @Override
    public String toString() {
        String format = String.format("%s %s (from: %s to: %s)", this.getTaskType()
                                    , super.toString(), this.start, this.end);
        return format;
    }

}
