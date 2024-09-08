package david.task;

/**
 * Todo task class
 */
public class TodoTask extends Task {

    /**
     * Contructor for todo task
     * @param eventName String event name
     * @param isCompleted
     */
    public TodoTask(String eventName, boolean isCompleted) {
        super(eventName, isCompleted);
    }

    @Override
    public String toCacheString() {
        String isCompleted = this.isCompleted() ? "1" : "0";

        return "T|" + isCompleted + "|" + this.getTask();
    }

    @Override
    public String toString() {
        String isCompleted = super.isCompleted() ? "X" : " ";

        return "[T]" + "[" + isCompleted + "] " + super.getTask();
    }
}
