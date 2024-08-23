/**
 * ToDo is a class that encapsulates the characteristics of a ToDo Task.
 * It extends from the class Task.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
