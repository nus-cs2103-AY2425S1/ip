public abstract class Task {
    private String info;
    private boolean isDone;

    public Task(String info) {
        this.info = info;
        this.isDone = false;
    }

    public String getInfo() {
        return info;
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
    public abstract String toString();
}
