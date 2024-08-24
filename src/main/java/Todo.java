public class Todo extends Task {

    public Todo (String TaskName) {
        super(TaskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
