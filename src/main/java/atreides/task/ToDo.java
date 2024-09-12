package atreides.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String write() {
        return "T" + super.write();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
