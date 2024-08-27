package dude;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String taskToStringData(){
        return "T" + super.taskToStringData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
