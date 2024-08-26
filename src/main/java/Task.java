public class Task {
    protected String name;
    protected boolean marked;

    public Task(String name) {
        this.name = name;
        this.marked = false;
    }

    public String getName() {
        return this.name;
    }

    public void setMark(boolean marked) {
        this.marked = marked;
    }

    @Override
    public String toString() {
        String marked = this.marked ? "[X]" : "[ ]";
        return marked + name;
    }
}

class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }
    @Override
    public String toString() {
        return "ToDo: " + super.toString();
    }
}
class Deadline extends Task {
    protected String by;
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }
    @Override
    public String toString() {
        return "Deadline: " + super.toString() + " (by: " + this.by + ")";
    }
}
class Event extends Task {
    protected String from;
    protected String to;
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "Event: " + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}