package Arona;

public class Todos extends Task {
    protected String by;

    /**
     * Constructor for the to do class which encapsulates a task with irrelevant/any start and end date
     * @param  description  the name of the task
     * @param  by  the deadline given in LocalDate readable format, time not include
     * */
    public Todos(String description) {
        super(description);
    }

    public String getCategory() {
        return "[T]";
    }

    public String toFriendlyString() {
        return super.toString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
