package task;

/**
 * A class representing individual todos.
 *
 * @author celeschai
 */
public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Todo todo)) {
            return false;
        }
        return super.equals(todo);
    }
}
