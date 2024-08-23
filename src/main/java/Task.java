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
                I have marked the following task as DONE :)""";
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String unmarkDoneToString() {
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

    public String addTaskToString() {
        return """
                I have added this task into the list for you and
                that brings your total number of tasks to""" + " " + String.valueOf(taskCount);
    }

    public String deleteTask() {
        taskCount--;
        return "Alright! I have removed this task for you." + "\n" +
                "You now have " + taskCount + " tasks left in your list.";
    }
}