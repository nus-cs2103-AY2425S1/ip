package Arona;

public class Todos extends Task {
    protected String by;

    /**
     * Constructor for the to do class which encapsulates a task with irrelevant/any start and end date
     * @param  description  the name of the task
     * */
    public Todos(String description) {
        super(description);
    }

    public String getCategory() {
        return "[T]";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
