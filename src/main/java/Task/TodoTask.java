package Task;

public class TodoTask extends Task{
    public TodoTask(String eventName) {
        super(eventName);
    }

    @Override
    public String toString() {
        String isCompleted = super.isCompleted() ? "X" : " ";
        return "[T]" + "[" + isCompleted + "] " + super.getTask() ;
    }
}
