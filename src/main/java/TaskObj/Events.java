package TaskObj;

public class Events extends Task {

    public final String fromDate;

    public final String toDate;

    public Events(String desc, String from, String to) {
        super(desc);
        this.fromDate = from;
        this.toDate = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + fromDate + " to:" + toDate + ")";
    }
}
