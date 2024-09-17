package Bwead;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a task with a name.
 */
public class Todo extends Task {

    private boolean isDone;
    private String text;

    /**
     * Constructs a todo task using a name.
     *
     * @param text todo's task name.
     */
    public Todo(String text) {
        super(text);
        this.text = text;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        assert this.text != null;
        return this.text;
    }

    /**
     * Returns the string representation of a todo task.
     *
     * @return String representation of an todo task.
     */
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
