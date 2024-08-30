public class Task {
    private String info;
    private boolean isDone;

    public Task(String info) {
        this.info = info;
        this.isDone = false;
    }

    public String getStatus() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }
    public void setToDone() {
        this.isDone = true;
    }
    public void setToUndone() {
        this.isDone = false;
    }
    public String toString() {
        return "["+getStatus()+"] "+info;
    }
}
