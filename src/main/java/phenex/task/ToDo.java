package phenex.task;

/**
 * ToDo class encapsulating a ToDo Task.
 */
public class ToDo extends Task {

    public ToDo(String name) {
        super(name, "T");
    }

    @Override
    public String toString() {
        return "[" + this.symbol + "]" + super.toString();
    }
}
