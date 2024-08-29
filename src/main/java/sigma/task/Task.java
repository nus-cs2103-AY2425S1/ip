package sigma.task;

public class Task {
    private boolean status;
    private String desc;

    public Task(String desc) {
        this.status = false;
        this.desc = desc;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public String getStatusString() {
        return status ? "X" : " ";
    }

    public String getTaskType() {
        return " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusString(), getDesc());
    }

}
