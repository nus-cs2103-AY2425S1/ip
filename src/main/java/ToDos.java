public class ToDos extends Task{

    public ToDos(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String getTaskType() {
        return "T";
    }
}
