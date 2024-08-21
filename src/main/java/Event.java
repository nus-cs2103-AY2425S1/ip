public class Event extends Task {
    String date1 = "";
    String date2 = "";

    public Event(String desc, String date1, String date2) {
        super(desc);
        this.date1 = date1;
        this.date2 = date2;
    }

    @Override
    public String stringValue() {
        return "[E]" + super.stringValue() + "(from: " + date1 + "to: " + date2 + ")";
    }
}
