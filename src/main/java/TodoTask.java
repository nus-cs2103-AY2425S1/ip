public class TodoTask extends Task {

    public TodoTask(String taskItem) {
        super(taskItem);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
