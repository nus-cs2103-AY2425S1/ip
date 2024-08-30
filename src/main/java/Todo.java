public class Todo extends Task{

    public Todo(String taskInfo, boolean isDone) {
        super(taskInfo, isDone);
    }

    @Override
    public String stringIsDone() {
        if (checkIsDone()) {
            return String.format("[T][X] %s", super.getTask());
        } else {
            return String.format("[T][ ] %s", super.getTask());
        }
    }

    @Override
    public String dataToSave() {
        if (checkIsDone()) {
            return String.format("TO-DO         | 1 | %s", super.getTask());
        } else {
            return String.format("TO-DO         | 0 | %s", super.getTask());
        }
    }

}
