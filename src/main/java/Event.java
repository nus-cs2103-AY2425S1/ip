public class Event extends Item {


    private String from;
    private String to;

    public Event(String newname, String from, String to) {
        super(newname);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String str = String.format("[E] %s (from: %s to: %s)", super.toString(), this.from, this.to);
        return str;
    }
    
}
