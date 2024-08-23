public class Event extends Task {
    String start;
    String end;
    public Event(String desc, String start, String end) {
        super(desc);
        int indexOfFrom = start.indexOf(" ") + 1;
        int indexOfEnd = end.indexOf(" ") + 1;
        this.start = start.substring(indexOfFrom);
        this.end = end.substring(indexOfEnd);
    }

    @Override
    public String getDesc() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                super.getStatusIcon(), super.getDesc(), this.start, this.end);
    }
}
