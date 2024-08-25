class Event extends Task {
    private final String startTime;
    private final String endTime;

    Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    Event(String name, String startTime, String endTime, boolean isDone) {
        super(name, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E][" + (this.isDone ? "X" : " ") + "] " + this.name
                + " (from: " + this.startTime
                + " to: " + this.endTime + ")";
    }

    @Override
    String toSaveState() {
        return "E" + "/" + (this.isDone ? "1" : "0") + "/"
                + this.name + "/" + this.startTime
                + "/" + this.endTime + "\n";
    }
}
