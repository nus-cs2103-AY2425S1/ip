public class Task {
    boolean isDone;
    String taskDes;

    public Task(String taskDes){
        this.taskDes = taskDes;
        isDone = false;
    }
    public void setDone(){
        isDone = true;
    }

    public void setUndone(){
        isDone = false;
    }

    public String toString(){
        if(isDone){
            return "[X]" + taskDes;
        } else {
            return "[ ]" + taskDes;
        }
    }
}