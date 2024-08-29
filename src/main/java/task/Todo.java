package task;

public class Todo extends Task {
    public Todo (String taskName){
        super(taskName);
    }
    public Todo (String taskName, boolean isCompleted){
        super(taskName, isCompleted);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String writeToFile() {
        return "T " + (super.isCompleted() ? "0" : "1") + " " + this.getTaskName();
    }
}
