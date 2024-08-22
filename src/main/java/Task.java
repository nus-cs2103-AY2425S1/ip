public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String markDoneToString() {
        return """
                Yay! One task down!
                I have marked the task as DONE :)
                  [X] """ + description;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String unmarkDoneToString() {
        return """
                Oh no! I shall mark the task as UNDONE :(
                You can do it!
                  [ ] """ + description;
    }

    public String taskToString() {
        if (isDone) {
            return "[X] " + description;
        }
        return "[ ] " + description;
    }
}
