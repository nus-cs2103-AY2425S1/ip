public class Tasks {
    //Description of task
    private String description;
    //Completion status of task
    private boolean isDone;

    public Tasks(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return description;
    }

    //Displays completion icon based on completion status
    protected String completionIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    //marks completion status as done
    protected void markDone() {
        isDone = true;
    }

    //marks completion status as not done
    protected void unmarkDone() {
        isDone = false;
    }

}
