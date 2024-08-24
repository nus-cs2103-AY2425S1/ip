public class Event extends Task{
    private String from;
    private String to;
    public Event(String description, String start, String end) {
        super(description);
        this.from = start;
        this.to = end;
    }

    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.from = start;
        this.to = end;
    }




    public String getStartTime() {
        return "from: " + this.from;
    }

    public String getEndTime() {
        return "to: " + this.to;
    }

    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + from + " | " + to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "("
                + this.getStartTime() + " "
                + this.getEndTime() + ")";
    }
}
