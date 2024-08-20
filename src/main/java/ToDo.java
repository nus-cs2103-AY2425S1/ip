public class ToDo extends Task {

    private ToDo(String name, TaskType taskType) {
        super(name, taskType);
    }

    public String getTaskTypeAsString(){
        return "T";
    }

    public static ToDo of(String name, TaskType taskType) {
        String[] parts = name.split(" ", 2);
        String taskName = parts[1];
        return new ToDo(taskName, TaskType.T);
    }
}
