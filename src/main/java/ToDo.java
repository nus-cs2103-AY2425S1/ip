import java.util.ArrayList;

/**
 * This class implements a ToDo task.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
