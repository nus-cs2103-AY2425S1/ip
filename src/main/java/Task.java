public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type= " ";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setMarkStatus(boolean status) {
        this.isDone = status;
    }

    public void setTypeStatus(String s) {
        this.type = s;
    }
}
