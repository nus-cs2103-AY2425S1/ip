package phenex.task;

/**
 * Abstract class Task which encapsulates a Task for the Phenex to keep track of.
 */
public abstract class Task {

    /**
     * Enumeration which encapsulates the different types of a task.
     */
    public enum TaskType {
        RECREATIONAL,
        PRODUCTIVE,
        OTHERS
    }

    public static final String OTHER_TASK_SYMBOL = "O";
    public static final String PRODUCTIVE_TASK_SYMBOL = "P";
    public static final String RECREATIONAL_TASK_SYMBOL = "R";

    protected final String name;
    protected boolean isCompleted;
    protected String taskSymbol;
    protected double hoursTaken;
    protected double defaultHours = 0;
    protected final TaskType taskType;

    /**
     * Creates a Task.
     * @param name the name of the task.
     * @param symbol the symbol of the task.
     */
    public Task(String name, String taskSymbol, TaskType taskType) {
        this.name = name;
        this.isCompleted = false;
        this.taskSymbol = taskSymbol;
        this.hoursTaken = defaultHours;
        this.taskType = taskType;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public String getTaskSymbol() {
        return this.taskSymbol;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    public String getTaskTypeSymbol() {
        switch (this.taskType) {
        case RECREATIONAL:
            return RECREATIONAL_TASK_SYMBOL;
        case PRODUCTIVE:
            return PRODUCTIVE_TASK_SYMBOL;
        case OTHERS:
            return OTHER_TASK_SYMBOL;
        default:
            return OTHER_TASK_SYMBOL;
        }
    }

    protected void setCompleted(double hoursTaken) {
        this.isCompleted = true;
        this.hoursTaken = hoursTaken;
    }

    protected void setUncompleted() {
        this.isCompleted = false;
        this.hoursTaken = defaultHours;
    }

    protected String getStatusIcon() {
        return this.isCompleted ? "X" : " ";
    }

    public double getHoursTaken() {
        return this.hoursTaken;
    }

    @Override
    public String toString() {
        String hoursString = (this.getStatusIcon().equals("X"))
                             ? "\n\t\tHours taken: " + this.hoursTaken
                             : "";
        return "["
                + this.getTaskTypeSymbol()
                + "]"
                + "["
                + this.getStatusIcon()
                + "] "
                + this.name
                + hoursString;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Task) {
            Task compared = (Task) object;
            return this.name.equals(compared.name) && this.taskType.equals(compared.taskType);
        }
        return false;
    }
}
