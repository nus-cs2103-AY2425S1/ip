class Event extends Task {
    String from;
    String to;

    Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    Event(int status, String task, String from, String to) {
        super(status, task);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "[E]" + super.toString() + 
            " (from: " + this.from + " to:" + this.to + ")";
    }

    String toSaveAsString() {
        return "E/" + super.toSaveAsString() + "/" + this.from + "/" + this.to;
    }
}