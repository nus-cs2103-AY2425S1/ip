package Bwead;

/**
 * Represents a task with a name.
 */
public class Todo extends Task {

    private boolean isDone;
    private String text;

    public Todo(String text) {
        super(text);
        this.text = text;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return this.text;
    }

    public String toString() {
        String str = "";
        if (this.isDone) {
            str = "X";
        } else {
            str = " ";
        }
        return "[T][" + str + "] " + this.text;
    }
}
