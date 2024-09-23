package lewis;

/**
 * This class implements a task which can have
 * (i) A description
 * (ii) A completion status
 * This class is abstract, and must be extended to a new subclass
 * to be instantiated.
 * Currently, the subclasses are: Event, Todo and Deadline
 */
public abstract class Task {
    protected Status status;
    private final String description;
    enum Status {
        DONE, NOT_DONE, URGENT
    }
    /**
     * Protected constructor for a Task.
     * Initialises the completion status to NOT_DONE by default.
     * @param description a string description of this task.
     */
    protected Task(String description) {
        this.description = description;
        this.status = Status.NOT_DONE;
    }

    /**
     * Protected constructor for a Task.
     * This should be used when loading an already initialised task
     * @param description a string description of this task
     * @param status the completion status of this task
     * */
    protected Task(String description, Status status) {
        this.description = description;
        this.status = status;
    }


    /**
     * Updates the status of this task to the indicated status
     * @param input the status that this task should update to.
     */
    @SuppressWarnings("checkstyle:MissingSwitchDefault")//enum input is used
    protected void updateStatus(Task.Status input) {
        StringBuilder changelog = new StringBuilder();
        changelog.append("Status updated successfully: \nOld: ");
        changelog.append(this);
        switch(input) {
        case DONE -> status = Status.DONE;
        case NOT_DONE -> status = Status.NOT_DONE;
        case URGENT -> status = Status.URGENT;
        }
        changelog.append("\nNew: ");
        changelog.append(this);
        System.out.println(changelog);
    }

    /**
     * Returns a specific character to output to the user to indicate
     * the status of this task
     * @return a character
     */
    private char getStatusIcon() {
        return switch (status) {
        case DONE -> 'X';
        case NOT_DONE -> ' ';
        case URGENT -> '!';
        };
    }

    /**
     * Returns a string representation of this task
     * @return a string
     */
    @Override
    public String toString() {
        return String.format("[%c] %s", getStatusIcon(), description);
    }

    /**
     * Converts this task into a comma-separated-variable representation
     * to be stored
     * @return this task as a csv string
     */
    protected String toCsv() {
        return this.description
                + ","
                + this.status;
    }
}
