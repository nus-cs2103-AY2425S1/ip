package Mario.Tasks;

public class Event extends Task {
    private final String start;
    private final String end;
    private String type;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
        this.type = "E";
    }

    public Event(String name, String start, String end, boolean isCompleted) {
        super(name, isCompleted);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getName() {
        String status;
        if (isCompleted) {
            status = "[X]";
        } else {
            status = "[ ]";
        }
        return "[E]" + status + " " + name + "(from: " + this.start + " to: " + this.end + ")";
    }
    @Override
    public String toFileFormat() {
        int status = this.isCompleted ? 1 : 0;
        return "E " + "| " + status + " | "
                + this.name + " | " + this.start + "-" + this.end;
    }

}
