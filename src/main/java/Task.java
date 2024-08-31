public abstract class Task {
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
            return "[X] " + taskDes;
        } else {
            return "[ ] " + taskDes;
        }
    }

    public String getIsDone() {
        if(isDone) {
            return "1";
        }
        return "0";
    }

    public  String getTaskDes() {
        return taskDes;
    }

    public abstract String getAdd();

    public abstract String getTypeLetter();

}