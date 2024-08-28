import java.time.LocalDate;
import java.util.*;

public class Task implements Comparable<Task> {
    private boolean isDone;
    private String name;
    protected LocalDate compareDate;

    public Task(String name) {
        this.isDone = false;
        this.name = name;
        this.compareDate = LocalDate.MIN;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    public void mark() throws InvalidMarkAndUnmarkException {
        if (this.isDone == true) {
            throw new InvalidMarkAndUnmarkException("Task is already marked as done!");
        } else {
            this.isDone = true;
        }
    }

    public void unmark() throws InvalidMarkAndUnmarkException {
        if (this.isDone == false) {
            throw new InvalidMarkAndUnmarkException("Task is still undone!");
        } else {
            this.isDone = false;
        }
    }
    @Override
    public int compareTo(Task t) {
        return this.compareDate.compareTo(t.compareDate);
    }
}