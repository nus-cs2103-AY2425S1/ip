package Bwead;

public class Task {

    public boolean isDone;
    public String text;

    public Task(String text) {
        this.isDone = false;
        this.text = text;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toString() {
        String str = "";
        if (isDone) {
            str = "X";
        } else {
            str = " ";
        }
        return "[" + str + "] " + text;
    }
}
