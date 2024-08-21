public abstract class Task {
    protected String description;
    protected TaskType type;
    public Task(String description, TaskType type){
        this.description = description;
        this.type = type;
    }
    // method to return TaskType
    protected abstract TaskType type();

}
