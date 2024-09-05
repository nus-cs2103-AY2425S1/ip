package darwin.task;

public class ToDo extends Task {
    /**
     * Initialises a ToDo task with name.
     * @param name A string of the task.Task's name.
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getSymbol() {
        return "T";
    }

    @Override
    public String toCsv() {
        return super.toCsv();
    }
}
