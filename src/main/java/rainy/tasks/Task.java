package rainy.tasks;
import java.time.LocalDate;
import rainy.database.*;
import rainy.rainyexceptions.*;

public class Task implements Comparable<Task> {
    private boolean isDone;
    protected String name;
    protected LocalDate compareDate;
    private UI ui;

    public Task(String name) {
        this.isDone = false;
        this.name = name;
        this.compareDate = LocalDate.MAX;
        this.ui = new UI();
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    public void mark() throws InvalidMarkAndUnmarkException {
        if (this.isDone == true) {
            throw new InvalidMarkAndUnmarkException(this.ui.taskDone());
        } else {
            this.isDone = true;
        }
    }

    public void unmark() throws InvalidMarkAndUnmarkException {
        if (this.isDone == false) {
            throw new InvalidMarkAndUnmarkException(this.ui.taskNotDone());
        } else {
            this.isDone = false;
        }
    }
    @Override
    public int compareTo(Task t) {
        return this.compareDate.compareTo(t.compareDate);
    }
}