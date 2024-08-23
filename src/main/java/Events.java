public class Events extends Task {
    protected String startDT;
    protected String endDT;
    public Events(String description, String[] startEnd ) {
        super(description);
        this.startDT = startEnd[0];
        this.endDT = startEnd[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDT + " to: " + this.endDT + ")";
    }
}
