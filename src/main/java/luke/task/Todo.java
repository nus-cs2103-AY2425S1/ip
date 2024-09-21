package luke.task;

/**
 * The {@code Todo} class represents the Todo task type. The Todo task is a simple task; it has a task name and
 * no additional data.
 * <p>
 * The task description is shown in the format:
 * <pre>
 * [T][X] Task Name
 * </pre>
 * where the "T" represents a "Todo" task, and the "X" (or blank) represents the completion status.
 * </p>
 * The save format for a Todo task is as follows:
 * <pre>
 * X | todo | Task Name
 * </pre>
 * where "X" represents that the task is marked as done, and "-" indicates it is not done.
 * </p>
 *
 * @see Task
 * @see Deadline
 * @see Event
 */
public class Todo extends Task {
    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String taskDescription() {
        return "[T]" + showMark() + " " + this.name;
    }

    @Override
    public String taskInSaveData() {
        return (isDone ? "X" : "-")
                + " | todo | "
                + name + "\n";
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        } else if (obj == null || getClass() != obj.getClass()) {
//            return false;
//        }
//        Todo task = (Todo) obj;
//        return this.name.equals(task.name);
//    }
}
