package task;

import java.time.LocalDateTime;

public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public void update(String newDesc, LocalDateTime fromTime, LocalDateTime toTime) {
        if (newDesc != null) {
            this.setTaskName(newDesc);
        }
    }
}
