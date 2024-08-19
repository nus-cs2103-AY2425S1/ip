public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.description;
    }

    //turns out i was looking at an old revision of the CS2103 website which had these unicode
    //numbers instead of the current semester's implementation of task.
    /*public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }*/
    //Note that the above code has unintended side effects when performing A-TextUiTesting.
    //So, I have opted to use a blank for undone and an 'X' for successfully marked. That is all.
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    //...
}
