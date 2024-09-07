package taskpack;

/**
 * Used to represent a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for
     * @param name
     */
    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean isCompleted) {
        super(name, isCompleted);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string that is more easily parseable by Parser when file is read upon start.
     * It takes the current task traits and stores them into a string. Similar to toString
     * method.
     * @return The String to be saved into the write file
     */
    @Override
    public String toParseableString() {
        String s = "t,";
        if (this.isCompleted()) {
            s += "m,";
        } else {
            s += "u,";
        }
        s += this.getName();
        return s;
    }
}
