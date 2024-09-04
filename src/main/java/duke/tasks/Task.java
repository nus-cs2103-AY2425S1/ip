package duke.tasks;
public class Task {
    public String task;
    private boolean isDone;

    public Task(String s){
        task = s;
        isDone = false;
    }
    public void complete(){
        isDone = true;
    }
    public void uncomplete(){
        isDone = false;
    }

    @Override
    public String toString() {
        String status;
        if(isDone) {
            status = "[X] ";
        }
        else {
            status = "[ ] ";
        }
        return status + task;
    }

    public String getTask() {
        return this.task;
    }
    public boolean isReal(){
        return task != "";
    }

}
