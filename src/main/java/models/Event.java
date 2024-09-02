package models;

public class Event extends Task {

    private String from;
    private String to;

    public Event(String description) {
        super(description);
    }

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }


    private String getFrom() {return this.from;}
    private String getTo() {return this.to;}

    public String serialize() {
        return String.format("E|%s|%s|%s|%s", this.getIsDone() ? "1" : "0", this.getDescription(),
        this.getFrom(), this.getTo()  );
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s(from: %sto: %s)", this.getStatusIcon(), this.getDescription(),
                this.getFrom(), this.getTo());
    }
}