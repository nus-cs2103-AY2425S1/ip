package Bwead;

/**
 * Represents a task that has a string method and can be marked to done.
 */
public class Task {

    public boolean done;
    public String text;

    public Task(String text) {
        this.done = false;
        this.text = text;
    }

    public void setDone(boolean toset) {
        this.done = toset;
    }

    public String toString() {
        String str = "";
        if (done) {
            str = "X";
        } else {
            str = " ";
        }
        return "[" + str + "] " + text;
    }
}
