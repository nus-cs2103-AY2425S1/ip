public class Event extends Task {

    private String start;
    private String end;

    public Event(String input, String start, String end) {
        super(input);
        this.start = start;
        this.end = end;
    }

    @Override
    public String displayTask() {
        String cross ="";
        if (super.getDone()) {
            cross = "[X]";
        } else {
            cross = "[ ]";
        }
        return "[E]" + cross + " " + super.getInput() + "(from:" + this.start + "to:" + this.end + ")\n";
    }
}
