package Diomon;

import java.time.LocalDate;

public class Event extends Task{
    public static final String TYPEICON = "E";
    LocalDate from;
    LocalDate to;
    public Event(String task, LocalDate from, LocalDate to) {
        super(task);
        this.from = from;
        this.to = to;
    }
    public Event(boolean complete, String description, LocalDate from, LocalDate to) {
        super(complete, description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String getTypeIcon() {
        return TYPEICON;
    }

    @Override
    public String toString() {
        return super.description + String.format(" (From: %s To: %s)", this.from.format(Parser.DATEFORMATTER), this.to.format(Parser.DATEFORMATTER));
    }

    @Override
    public String toStorageString(){
        return String.format("%s|%s|%s|%s|%s", getTypeIcon(), getStatusIcon(),this.description, this.from.format(Parser.DATEFORMATTER), this.to.format(Parser.DATEFORMATTER));
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Event temp) {
            return temp.from.equals(from) && temp.to.equals(to) && temp.completed == completed && temp.description.equals(description);
        }
        return false;
    }
}