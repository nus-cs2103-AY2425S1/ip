public class Todo extends Task{

    public Todo(String taskInfo) {
        super(taskInfo);
    }

    @Override
    public String stringIsDone() {
        if (checkIsDone()) {
            return String.format("[T][X] %s", super.getTask());
        } else {
            return String.format("[T][ ] %s", super.getTask());
        }
    }

}
