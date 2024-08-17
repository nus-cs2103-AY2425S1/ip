public class Todo extends Task {
    public Todo(String description) {
        super(description); // call the constructor of the parent class simple task
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
