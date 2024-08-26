package rotodo.tasklist;

/**
 * The Deadline class encapsulates the specific type 
 * of Task, called Deadlines. Events are tasks that 
 * need to be done before a specific date/time.
 * 
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class Deadline extends Task {
    String by;

    public Deadline(String ...value) {
        super(value[0], value.length > 2 ? value[2] : "0");
        this.by = value[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String saveString() {
        return "D | " + saveString(by);
    }
}
