package phenex.task;

/**
 * ToDo class encapsulating a ToDo Task.
 */
public class ToDo extends Task {

    public ToDo(String name, TaskType taskType) {
        super(name, "T", taskType);
    }

    @Override
    public String toString() {
        return "[" + this.taskSymbol + "]" + super.toString();
    }
}
