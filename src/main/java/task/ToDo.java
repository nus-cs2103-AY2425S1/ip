package task;

public class ToDo extends Task {
    public ToDo(String taskName){
        super(taskName);
    }
    public ToDo(String taskName, boolean isCompleted){
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
