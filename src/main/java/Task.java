public class Task {
    private String task;
    private boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public String getDone() {
        if (this.done)
            return "X";
        else
            return " ";
    }

    public void changeDone(boolean state) {
        this.done = state;
    }

    @Override
    public String toString() {
        return "["+ this.getDone()+"] "+ this.task;
    }
}
