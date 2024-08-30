package task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type= type;
    }

    public String getType() {
        return this.type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setMarkStatus(boolean status) {
        this.isDone = status;
    }

    public String toSavedFormat(String separation) {
        return type + separation + (isDone ? "1" : "0") + separation + description;
    }

    public void convertSavedDataToTask(String[] dataArr) {
        this.isDone = dataArr[1].equals("1");
        this.description = dataArr[2];
    }

    @Override
    public String toString() {
        return this.description;
    }

}
