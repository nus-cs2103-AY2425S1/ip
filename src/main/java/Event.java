public class Event extends Task{

    String start;
    String end;

    public Event(String str, String start, String end) {
        super(str);
        this.start = start.substring(0, 4) + ":" + start.substring(4);
        this.end = end.substring(0, 2) + ":" + end.substring(2);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + start + " " + end + ")";
    }

}
