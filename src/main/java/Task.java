public class Task {
    protected String desc;
    protected boolean isDone;
    
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public void toggleStatus() {
        isDone = isDone ? false : true;
    }

    public String getStatus() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDesc() {
        return desc;
    }
}
