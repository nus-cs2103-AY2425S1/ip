public class Event extends Task{
    private String from;
    private String to;
    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return this.to;
    }

    public String getFrom() {
        return this.from;
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return super.description + String.format(" (From: %s To: %s)", this.from, this.to);
    }

    @Override
    public String toStorageString(){
        return String.format("%s|%s|%s|%s|%s", getTypeIcon(), getStatusIcon(),this.description, this.from, this.to);
    }
}