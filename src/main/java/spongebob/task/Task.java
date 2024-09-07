package spongebob.task;

/**
 * basic task
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;
    protected String tag;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
        this.tag = "";
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String toString() {
        return this.getStatusIcon() + this.getDescription() + " ";
    }
    /**
     * converts task into a string that can be stored and read later
     * @return String version of the task
     */
    public String save() {
        return this.taskType + "|" + this.isDone + "|" + this.description
                + "|" + this.tag;
    }

    public boolean contains(String keyword) {
        return this.description.contains(keyword);
    }

    public String getTag() {
        if (this.tag.equals("")) {
            return "";
        } else {
            return " #" + this.tag;
        }
    }
}
