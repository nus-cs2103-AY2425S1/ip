public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString() + getDetails();
    }

    @Override
    public String getDetails() {
        return "";
    }
}
