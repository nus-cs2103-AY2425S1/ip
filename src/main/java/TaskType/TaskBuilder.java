package TaskType;

public class TaskBuilder {
    private String description;
    private String taskType;
    private String by;
    private String from;
    private String to;

    public TaskBuilder(String description, String taskType) {
        this.description = description;
        this.taskType = taskType;
    }

    public TaskBuilder by(String by) {
        this.by = by;
        return this;
    }

    public TaskBuilder from(String from) {
        this.from = from;
        return this;
    }

    public TaskBuilder to(String to) {
        this.to = to;
        return this;
    }

    public Task build() {
        switch (taskType) {
            case "todo":
                ToDo todo = new ToDo(description);
                System.out.println(todo);
                return todo;
            case "deadline":
                if (by != null) {
                    Deadline deadline = new Deadline(description, by);
                    System.out.println(deadline);
                    return deadline;
                } else {
                    throw new IllegalArgumentException("deadline requires a 'by' parameter");
                }
            case "event":
                if (from != null && to != null) {
                    Event event = new Event(description, from, to);
                    System.out.println(event);
                    return event;
                } else {
                    throw new IllegalArgumentException("event requires 'from' and 'to' parameter");
                }
            default:
                throw new IllegalArgumentException("invalid task type.");
        }
    }
}
