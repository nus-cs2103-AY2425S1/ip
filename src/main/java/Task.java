public class Task {
    protected String description;
    protected boolean isDone;
    private static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount++;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String markDoneToString() {
        return """
                Yay! One task down!
                I have marked the following task as DONE :)
                  [X]""" + " " + description;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String unmarkDoneToString() {
        return """
                Oh no! I shall mark the following task as UNDONE :(
                You can do it!
                  [ ]""" + " " + description;
    }

    public String taskToString() {
        if (isDone) {
            return "[X] " + description;
        }
        return "[ ] " + description;
    }

    public String addTaskToString() {
        return """
                I have added this task into the list for you and
                that brings your total number of tasks to""" + " " + String.valueOf(taskCount);
    }
}
