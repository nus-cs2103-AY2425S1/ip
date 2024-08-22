public abstract class Task{
    private final String description;
    private boolean isDone = false;

    public Task(String description){
        this.description = description;
    }
    public void setDone(){
        this.isDone = true;
    }
    public void setUndone(){
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    @Override
    public String toString(){
        return String.format("[%s] %s", getStatusIcon(),this.description);
    }

}