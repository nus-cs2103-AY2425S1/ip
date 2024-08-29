public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(boolean isDone, String description, String from, String to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String getSavedDataString() {
        return "E" + " | " + super.getSavedDataString() + " | " + from + " | " + to;
    }

    public static Task getTaskFromSavedDataStringArr(String[] savedDataArr) {
        return new Event(getStatusBoolean(Integer.parseInt(savedDataArr[1])), savedDataArr[2], savedDataArr[3], savedDataArr[4]);
    }
}
