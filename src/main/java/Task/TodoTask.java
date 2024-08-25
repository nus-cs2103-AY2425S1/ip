package Task;

public class TodoTask extends Task {
    public TodoTask(String eventName, boolean completed) {
        super(eventName, completed);
    }

    @Override
    public String toCacheString() {
        return "T|" + (this.isCompleted() ? "1" : "0") + "|" + this.getTask();
    }

    @Override
    public String toString() {
        String isCompleted = super.isCompleted() ? "X" : " ";
        return "[T]" + "[" + isCompleted + "] " + super.getTask() ;
    }
}
