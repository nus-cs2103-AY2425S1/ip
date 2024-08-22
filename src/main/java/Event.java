public class Event extends Task{
    protected String due;
    public Event(String description, String due) {
        super(description);
        this.due = due;
    }
    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        return String.format("[T] [%s] %s (by: %s)", mark, super.description, this.due);
    }
}
