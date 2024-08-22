/**
 * This class represents tasks without any date/time attached to it
 */
public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
