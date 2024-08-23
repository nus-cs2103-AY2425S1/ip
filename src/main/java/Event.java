public class Event extends Task{
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String addTaskToString() {
        return super.addTaskToString() + "\n" + "  [E] [ ] " + description + " (" + start + " - " + end + ")";
    }

    @Override
    public String taskToString() {
        if (isDone) {
            return "[E] [X] " + description + " (" + start + " - " + end + ")";
        }
        return "[E] [ ] " + description + " (" + start + " - " + end + ")";
    }

}