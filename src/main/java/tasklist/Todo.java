package tasklist;

/**
 * The Todo class encapsulates the specific type of 
 * Task, called ToDos. ToDos are tasks without any 
 * date/time attached to it.
 * 
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class Todo extends Task {
    public Todo(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
