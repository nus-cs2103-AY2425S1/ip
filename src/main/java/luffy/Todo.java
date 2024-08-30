package luffy;

public class Todo extends Task{

    public Todo(String taskInfo, boolean isDone) {
        super(taskInfo, isDone);
    }

    @Override
    public String stringIsDone() {
        if (checkIsDone()) {
            return String.format("[T][X] %s", super.getTaskInfo());
        } else {
            return String.format("[T][ ] %s", super.getTaskInfo());
        }
    }

    @Override
    public String dataToSave() {
        if (checkIsDone()) {
            return String.format("TO-DO         | 1 | %s", super.getTaskInfo());
        } else {
            return String.format("TO-DO         | 0 | %s", super.getTaskInfo());
        }
    }

}