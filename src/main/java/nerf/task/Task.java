package nerf.task;

public abstract class Task{
    private final String description;
    private boolean isDone = false;

    public Task(String description){
        this.description = description;
    }
    public Task(String description,boolean status){
        this.description = description;
        this.isDone = status;
    }

    public void setDone(){
        this.isDone = true;
    }
    public void setUndone(){
        this.isDone = false;
    }

    /**
     * Returns true if description has keyword in it.
     * Returns false otherwise.
     * 
     * @param keyword to check.
     * @return true if keyword in description.
     */
    public boolean matchSearch(String keyword) {
        return this.description.contains(keyword);
    }

    private String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public String getSaveFormat(){
        return String.format("%d | %s", this.isDone?1:0, this.description);
    }

    @Override
    public String toString(){
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

}