public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description= description;
        this.isDone = false;
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
