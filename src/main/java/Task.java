import java.io.File;

public class Task {
    private String task;
    private boolean done;
    protected static int count = 0;

    public Task(String task) {
        this.task = task;
        this.done = false;
        count++;
    }

    public Task(String task, boolean done) {
        this.task = task;
        this.done = done;
        count++;
    }

    public String getDoneX() {
        if (this.done)
            return "X";
        else
            return " ";
    }

    public String getDone1() {
        if (this.done)
            return "1";
        else
            return "0";
    }

    public void changeDone(boolean state) {
        this.done = state;
    }

    public static void deleteTask() {
        count--;
    }

    public String write_to_datafile(File dataFile){
        return this.task;
    }

    @Override
    public String toString() {
        return "["+ this.getDoneX()+"] "+ this.task;
    }
}
