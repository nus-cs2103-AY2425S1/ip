public class Event extends Task {
    public Event(String task) {
        super(task);
    }

    public String toString() {
        return "[E]" + super.toString();
    }


}
