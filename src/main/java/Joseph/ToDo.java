package Joseph;

public class ToDo {
    private String desc;
    private boolean isDone;

    public ToDo(String desc) {
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
