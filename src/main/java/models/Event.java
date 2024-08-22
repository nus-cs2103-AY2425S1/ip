package models;

public class Event extends Task {

    private String from;
    private String to;

    public Event(String description) {
        super(description);
    }


    private String getFrom() {return this.from;}
    private String getTo() {return this.to;}

    @Override
    public String toString() {
        return String.format("[E] [%s] %s (from: %s to: %s)", this.getStatusIcon(), this.getDescription(),
                this.getFrom(), this.getTo());
    }
}