package Bwead;

/**
 * Represents a task that has a string method and can be marked to done.
 */
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

    public String getName() {
        return this.text;
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
