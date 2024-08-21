public class Task {
    private String task;
    private boolean done;
    protected static int count = 0;

    public Task(String task) {
        this.task = task;
        this.done = false;
        count++;
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
