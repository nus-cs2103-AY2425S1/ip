public class Event extends Task{
    private String start;
    private String end;
    public Event(String desc, Boolean mark, String start, String end) throws MissingDescriptionException{
        super(desc, mark);
        this.start = start;
        this.end = end;
    }

    @Override
    public String printTask() {
        return String.format("[E]%s (from: %s to: %s)", super.printTask(), start, end);
    }
}
