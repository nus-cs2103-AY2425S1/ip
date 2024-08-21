package todo;

/**
 * A class representing individual todos
 *
 * @author celeschai
 * @version 1.0 22 Aug 2023
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
