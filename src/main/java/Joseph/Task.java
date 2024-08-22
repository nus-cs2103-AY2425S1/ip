package Joseph;

public class Task {
    private String desc;
    private boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }
    public String getDone() {
        return isDone ? "X" : " ";
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDone() {
        this.isDone = true;
    }
    public void unDone() {
        this.isDone = false;
    }

}
