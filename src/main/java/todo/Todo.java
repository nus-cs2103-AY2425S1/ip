package todo;

/**
 * A class representing individual todos
 *
 * @author celeschai
 */
public class Todo extends Task{
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
