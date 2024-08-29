abstract class Task {
    private boolean isDone;
    private String name;

    Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    public void changeStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.name);
    }

    public String convertToTxt() {
        return String.format("%s,%s", this.isDone, this.name);
    }
}
