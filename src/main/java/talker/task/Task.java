package talker.task;

/**
 * Represents a Task that has a description and can be marked as complete/incomplete
 */
public class Task {
    // String describing task
    protected String description;
    // Status of task
    protected boolean complete;
    // Type of task
    protected TaskType taskType;
    // Priority of task, default set to low
    protected PriorityType priorityType;

    /**
     * Constructs a new Task object, marks task as incomplete to start
     *
     * @param description description of task
     * @param type type of task
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.complete = false;
        this.taskType = type;
        this.priorityType = PriorityType.LOW;
    }

    /**
     * Constructs a new Task object, marking task according to isComplete
     *
     * @param description description of task
     * @param type type of task
     * @param isComplete status of task completion
     * @param priorityType priority of task
     */
    public Task(String description, TaskType type, boolean isComplete, PriorityType priorityType) {
        this.description = description;
        this.complete = isComplete;
        this.taskType = type;
        this.priorityType = priorityType;
    }

    /**
     * Returns string representing status of task
     *
     * @return "X" if completed else " "
     */
    private String getComplete() {
        return this.complete ? "X" : " ";
    }

    /**
     * Returns string representing priority of task
     *
     * @return String "H" if high, "M" if medium, "L" if low
     */
    private String getPriorityToPrint() {
        switch (priorityType) {
        case LOW:
            return "L";
        case MEDIUM:
            return "M";
        case HIGH:
            return "H";
        default:
            assert priorityType != PriorityType.LOW;
            assert priorityType != PriorityType.MEDIUM;
            assert priorityType != PriorityType.HIGH;
        }
        return "";
    }

    /**
     * Returns priority of task
     *
     * @return PriorityType priority of current task
     */
    public PriorityType getPriorityType() {
        return this.priorityType;
    }

    public String setPriority(PriorityType priorityType) {
        this.priorityType = priorityType;

        String priorityString = "";
        switch (priorityType) {
        case LOW:
            priorityString = "LOW";
            break;
        case MEDIUM:
            priorityString = "MEDIUM";
            break;
        case HIGH:
            priorityString = "HIGH";
            break;
        default:
            assert priorityType != PriorityType.LOW;
            assert priorityType != PriorityType.MEDIUM;
            assert priorityType != PriorityType.HIGH;
        }
        return String.format("Okay, I've marked this task as %s priority for you:\n%s", priorityString, this);
    }

    /**
     * Returns boolean representing status of task
     *
     * @return true if complete else false
     */
    public boolean isComplete() {
        return this.complete;
    }

    /**
     * Marks task as complete
     *
     * @return String describing task
     */
    public String mark() {
        this.complete = true;
        return String.format("Nice! I've marked this task as done:\n%s", this);
    }

    /**
     * Marks task as incomplete
     *
     * @return String describing task
     */
    public String unmark() {
        this.complete = false;
        return String.format("OK, I've marked this task as not done yet:\n%s", this);
    }

    /**
     * Returns String representation of task needed for file writing
     *
     * @return String in format specified to write to file
     */
    public String getSaveFormat() {
        String type = "";

        switch (taskType) {
        case TODO:
            type = "T";
            break;
        case DEADLINE:
            type = "D";
            break;
        case EVENT:
            type = "E";
            break;
        default:
            assert taskType != taskType.TODO;
            assert taskType != taskType.DEADLINE;
            assert taskType != taskType.EVENT;
        }

        return String.format("%s | %s | %s | %s",
                type,
                this.getComplete(),
                this.getPriorityToPrint(),
                this.description);
    }

    /**
     * Returns String representation of description of task
     *
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns String representation of task
     *
     * @return String showing status and description
     */
    public String toString() {
        return String.format("[%s][%s] %s", this.getComplete(), this.getPriorityToPrint(), this.description);
    }
}
