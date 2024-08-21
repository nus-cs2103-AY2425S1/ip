public class Task {
    private String taskname;
    private boolean done;
    public Task(String s) {
        taskname = s;
        done = false;
    }

    public void done() {
        done = true;
    }

    public void undone() {
        done = false;
    }

    @Override
    public String toString() {
        String str = "";
        if (done) {
            str = "[X] ";
        } else {
            str = "[ ] ";
        }
        str = str + taskname;
        return str;
    }
}
