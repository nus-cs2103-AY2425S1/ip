package topaz.task;

/**
 * Represents a Todo task with a description and a completion status.
 * Inherits from {@link Task}.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileRecord() {
        Integer status = isDone ? 1 : 0;
        return "T" + " | " + status + " | " + this.description;
    }
    @Override
    public String getStatus() {
        return "[T]" + "[" + super.getStatusIcon() + "]" + " " + super.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Todo) {
            Todo obj = (Todo) object;
            return obj.description.equals(this.description)
                    && this.isDone == obj.isDone;
        }
        return false;
    }
}
