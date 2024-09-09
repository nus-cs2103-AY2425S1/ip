public class Event extends Task {
    private String from;
    private String to;
    public Event(String info, String from, String to) {
        super(info);
        this.from = from;
        this.to = to;
    }


    @Override
    public String toString() {
        return "[E]" + "[" + getStatus().getStatusSymbol() +"]"+ " " + getInfo() + " " + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (getStatus() == TaskStatus.DONE ? "1" : "0") + " | " + getInfo() + " | " + from + " | " + to;
    }
}
