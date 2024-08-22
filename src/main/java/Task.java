public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon()
                + "] " + this.getDescription();
    }

    public Task createToDoTask(String taskDescription) throws InvalidTask {
        if (taskDescription == null || taskDescription.isEmpty()) {
            throw new InvalidTask("Missing description for ToDo task");
        }
        return new ToDo(taskDescription);
    }

    public Task createDeadlineTask(String taskDescription, String byDate) throws InvalidTask {
        if (taskDescription == null || taskDescription.isEmpty()) {
            throw new InvalidTask("Missing description for Deadline task");
        }
        if (byDate == null || byDate.isEmpty()) {
            throw new InvalidTask("Missing by date for Deadline task");
        }
        return new Deadline(taskDescription, byDate);
    }

    public Task createEventTask(String taskDescription, String fromDate, String toDate) throws InvalidTask {
        if (taskDescription == null || taskDescription.isEmpty()) {
            throw new InvalidTask("Missing description for Event task");
        }
        if (fromDate == null || fromDate.isEmpty()) {
            throw new InvalidTask("Missing from date for Event task");
        }
        if (toDate == null || toDate.isEmpty()) {
            throw new InvalidTask("Missing to date for Event task");
        }
        return new Event(taskDescription, fromDate, toDate);
    }
}
