public class EventTask extends Task {
    private String from;
    private String to;

    public EventTask(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", getStatusString(), getDesc(), from, to);
    }

}
