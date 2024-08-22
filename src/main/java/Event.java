public class Event extends Task {

    public boolean done;
    public String text;
    public String start;
    public String end;

    public Event(String text, String start, String end) {
        super(text);
        this.text = text;
        this.start = start;
        this.end = end;
    }
    public void setDone(boolean toset) {
        this.done = toset;
    }

    public String toString() {
        String str = "";
        if (done) {
            str = "X";
        } else {
            str = " ";
        }
        return "[E][" + str + "] " + text + "(from:" + start + "to:" + end + ")";
    }
}
