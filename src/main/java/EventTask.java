public class EventTask extends Task {
    String start;
    String end;
    public EventTask(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String s = "";
        s += "[E]";
        s += super.toString();
        s += super.taskDesc;
        s += String.format(" (from: %s to: %s) ", this.start, this.end);
        return s;
    }
}
