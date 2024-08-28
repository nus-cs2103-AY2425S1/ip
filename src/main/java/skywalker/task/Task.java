package skywalker.task;


public class Task {
    public String description;
    public boolean isDone;
    protected TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markDone(){
        if(!this.isDone) {
            this.isDone = true;
        } else{
            System.out.println("the task is done liao");
        }
    }

    public void unmarkDone() {
        if(this.isDone) {
            this.isDone = false;
        } else {
            System.out.println("the task is alr not done");
        }
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon()+ "] " + description;
    }
}
