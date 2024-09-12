package atreides.task;

public class Events extends Task {
    protected String startDT;
    protected String endDT;

    public Events(String description, String[] startEnd) {
        super(description);
        this.startDT = startEnd[0];
        this.endDT = startEnd[1];
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (obj instanceof Events other) {
            return other.startDT.equals(this.startDT) && other.endDT.equals(this.endDT);
        }
        return false;
    }

    @Override
    public String write() {
        return "E" + super.write() + " | " + startDT + "-" + endDT;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDT + " to: " + this.endDT + ")";
    }
}
