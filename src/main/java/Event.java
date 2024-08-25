public class Event extends Task {
    private String type = "E";
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getTaskInfo() {
        return(String.format("%s, (from: %s to: %s)", super.description, this.start, this.end));
    }

    public String getTaskType() {
        return(this.type);
    }
}
