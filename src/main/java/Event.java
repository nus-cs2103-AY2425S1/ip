public class Event extends Task {
    private final String from;
    private final String to;
    private static final String formatString = "[E][%s] %s (from:%s to: %s)";

    public Event(String description, boolean isComplete, String from, String to){
        super(description,isComplete);
        this.from = from;
        this.to= to;
    }

    @Override
    public String toString() {
        return String.format(formatString,this.isComplete?"X":" ",this.description,this.from, this.to);
    }


    @Override
    public Event mark() {
        return new Event(this.description, true,this.from, this.to);
    }

    @Override
    public Event unmark() {
        return new Event(this.description, false, this.from, this.to);
    }
}
