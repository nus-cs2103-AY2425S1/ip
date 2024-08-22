public class Event extends Task {
    private String symbol = "E";
    private String start;
    private String end;
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }
    @Override
    public String getSymbol() {
        return "E";
    }

    @Override
    public String getTaskInfo() {
        return super.getTaskInfo() + String.format(" (from: %s to: %s)", this.start, this.end);
    }
}
