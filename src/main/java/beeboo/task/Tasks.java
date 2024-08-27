package beeboo.task;

public abstract class Tasks {

    //Description of task
    protected String description;

    //Completion status of task
    protected boolean isDone;

    public Tasks(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return completionIcon() + " " + description;
    }

    //Displays completion icon based on completion status
    protected String completionIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    //Marks completion status as done
    public void markDone() {
        isDone = true;
    }

    //Marks completion status as not done
    public void unmarkDone() {
        isDone = false;
    }

    //Type icon for tasks implemented by the different type of tasks
    protected abstract String typeIcon();

    public abstract String saveFormat();

}
