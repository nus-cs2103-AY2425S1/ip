public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription(){
        return "[" + this.getStatusIcon() + "] "+this.description;
    }

    public void setDone(){
        this.isDone = true;
    }

    public void setUnDone(){
        this.isDone = false;
    }

}
