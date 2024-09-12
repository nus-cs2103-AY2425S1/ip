package darwin.task;

public class ToDo extends Task {
    private String symbol = "T";

    /**
     * Initialises a ToDo task with name.
     * @param name A string of the task.Task's name.
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toCsv() {
        return super.toCsv();
    }
}
