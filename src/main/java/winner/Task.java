package winner;

public class Task {
    private static int taskCount = 0;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount++;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public String markDone() {
        this.isDone = true;
        return """
                Yay! One task down!
                I have marked the following task as DONE :)""";
    }

    public String unmarkDone() {
        this.isDone = false;
        return """
                Oh no! I shall mark the following task as UNDONE :(
                You can do it!""";
    }

    public String taskToString() {
        if (isDone) {
            return "[X] " + description;
        }
        return "[ ] " + description;
    }

    public String deleteTask() {
        taskCount--;
        return "Alright! I have removed this task for you." + "\n"
                + "You now have " + taskCount + " tasks left in your list.";
    }
}
