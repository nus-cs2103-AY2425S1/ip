package bean.task;

public class TodoTask extends Task {

    public TodoTask(String name) {
        super(name);
    }

    @Override
    public String getDetails() {
        return "";
    }

    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + getDetails();
    }
}
