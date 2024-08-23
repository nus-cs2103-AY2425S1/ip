public class Event extends Task{
    String fromdate;
    String todate;
    public Event(String descr, String start, String end) {
        super(descr);
        fromdate = start;
        todate = end;
    }


    public String toString() {
        return "[E]" + super.toString() + "(from: " + fromdate + " to: " + todate + ")";
    }
}
