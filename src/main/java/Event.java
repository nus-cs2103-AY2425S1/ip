public class Event extends ToDo{
    public Event(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
