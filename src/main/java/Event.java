public class Event extends Task{

    protected String dateStart;
    protected String dateEnd;

    public Event(String description, String dateStart, String dateEnd) {
        super(description);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.dateStart + " to: " + this.dateEnd + ")";
    }
}
