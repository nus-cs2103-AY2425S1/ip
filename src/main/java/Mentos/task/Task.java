package Mentos.task;


public class Task {
    protected boolean isDone;
    protected String description;


    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }
    public void markAsDone(){
        this.isDone = true;
    }
    public String getDescription(){
        return this.description;
    }
    public void markAsNotDone(){
        this.isDone = false;
    }



    @Override
    public String toString(){
        return String.format("[%s] %s",this.getStatusIcon(),this.description);
    }
}