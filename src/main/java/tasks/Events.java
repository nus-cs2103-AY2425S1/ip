package tasks;

public class Events extends Task {
    private String start;
    private String end;
    public Events(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getAdditionalInfo() {
        return start + "|" + end;
    }

    @Override
    public String toFileString() {
        return getType() + "|" + getName() + "|" + isComplete() + "|" + getAdditionalInfo();
    }

    public String getFrom() {
        return start;
    }

    public String getTo() {
        return end;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(" + start + end + ")";
    }
}
