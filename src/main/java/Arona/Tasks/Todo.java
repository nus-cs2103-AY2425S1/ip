package arona.Tasks;

public class Todo extends Task {
    protected String by;

    /**
     * Constructor for the to do class which encapsulates a task with irrelevant/any start and end date
     * @param  description  the name of the task
     * */
    public Todo(String description) {
        super(description);
    }

    public String getCategory() {
        return "[T]";
    }

    @Override
    public String toString() {
        return getStatusIcon() + getCategory() + " " + getDescription();
    }
}
