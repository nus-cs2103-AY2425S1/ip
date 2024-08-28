public class TaskToJSONHelper {
    enum TaskType { ToDo, Event, Deadline }
    private final String[] arr = new String[3];
    private final TaskType taskType;
    private final Boolean done;
    public TaskToJSONHelper(Task task) {
        this.done = task.isDone();
        this.arr[0] = task.description;

        if (task instanceof ToDo) {
            this.taskType = TaskType.ToDo;
        } else if (task instanceof Deadline) {
            this.taskType = TaskType.Deadline;
            this.arr[1] = ((Deadline) task).endDate;
        } else {
            this.taskType = TaskType.Event;
            this.arr[1] = ((Event) task).endDate;
            this.arr[2] = ((Event) task).startDate;
        }
    }
    public TaskType getTaskType() {
        return this.taskType;
    }
    public Boolean getDone() {
        return this.done;
    }
    public String getDescription(){
        return this.arr[0];
    }
    public String getEndDate(){
        return this.taskType == TaskType.ToDo ? null : this.arr[1];
    }
    public String getStartDate(){
        return this.taskType == TaskType.Deadline ? this.arr[2] : null;
    }
}
