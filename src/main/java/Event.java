public class Event extends Task{

    private String start;
    private String end;
    private Task task;

    public Event(String description, String start, String end) {
        super(description);
        this.task = new Task(this.description);
        this.start = start.replaceFirst("from ", "");
        this.end = end.replaceFirst("to ", "");
    }

    @Override
    public String markTask() {
        return String.format("[E] %s (from: %s to: %s)", this.task.markTask() , this.start, this.end);
    }

    @Override
    public String unmarkTask() {
        return String.format("[E] %s (from: %s to: %s)", this.task.unmarkTask() , this.start, this.end);
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", this.task.toString() , this.start, this.end);
    }

}
