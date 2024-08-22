public class ToDos extends Task{
    public ToDos(String description) {
        super(description.substring(5));
        taskType = "[T]";
    }
}
