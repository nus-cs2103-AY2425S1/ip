public class ToDo extends Task {
    /**
     * Initialises a ToDo Task with name.
     * @param name A string of the Task's name.
     */
    public ToDo(String name) {
        super(name);
    }
    @Override
    public String getSymbol() {
        return "T";
    }
}
