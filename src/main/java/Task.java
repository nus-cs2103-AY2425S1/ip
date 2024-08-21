public abstract class Task {
    private String description;
    private boolean isDone;

    protected TaskType type;
    public Task(String description, TaskType type){
        this.description = description;
        this.type = type;
    }
    public void markAsDone(){
        this.isDone = true;
    }
    public void unmarkTask(){
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String getDescription(){
        return this.description;
    }

    // method to return TaskType
    protected abstract TaskType type();

}
