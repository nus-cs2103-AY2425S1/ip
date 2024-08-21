public class Task {
    private String task;
    private boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public String getTask() {
        return task;
    }

    public String getDone() {
        if (done)
            return "X";
        else
            return " ";
    }

    public void changeDone(boolean state) {
        this.done = state;
    }
}
