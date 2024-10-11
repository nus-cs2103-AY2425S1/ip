package arona.Tasks;

public class Task {
    protected String description;
    protected boolean isDone; // True means task is done

    /**
     * Effectively an interface encapsulating generic task objects, should not be initialised
     * @param  description  the name of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getCategory() {
        return "[ ]";
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }// X means task is done

    public void setStatus(boolean newStatus) {
        isDone = newStatus;
    }

    public String getDescription() {
        return description;
    }

    public String toFriendlyString() {
        return this.toString();
    }

    @Override
    public String toString() {
        return getStatusIcon() + getCategory() + " " + getDescription();
    }

}