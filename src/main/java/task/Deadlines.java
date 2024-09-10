package task;

import java.io.IOException;
import java.time.LocalDateTime;
/**
 * Represents a deadline task in a task management system.
 * A deadline task is a type of task that has a specific due date and time by which it must be completed.
 * This class extends the Task class and provides additional functionality specific to deadline tasks.
 */
public class Deadlines extends Task {
    private LocalDateTime date;
    /**
     * Constructs a new Deadline task with the specified name and due date.
     *
     * @param name Name of the deadline task.
     * @param date Due date and time of the deadline task.
     * @throws IOException If an I/O error occurs while initializing the deadline task.
     */
    public Deadlines(String name, LocalDateTime date) throws IOException {
        super(name, "D");
        this.date = date;
    }
    /**
     * Adds the deadline task to the task list and updates the storage with the task information.
     *
     * @param d Deadline task to be added.
     * @return Message indicating the result of the addition operation.
     * @throws IOException If an I/O error occurs while adding the task or updating the storage.
     */
    public String addTask(Deadlines d) throws IOException {
        TaskList.addTasks(d);
        updateTasklist(d);

        return ui.addDeadlineMessage(d, date);
    }

    private void updateTasklist(Deadlines d) throws IOException {
        String marked = "[X]";
        String unmarked = "[_]";
        int index = TaskList.tasks.size();
        StringBuilder information;
        if (d.getCurrentStatus() == Status.MARKED) {
            information = new StringBuilder(index + ". [" + d.getTag() + "]" + marked + " " + d.getName());
        } else {
            information = new StringBuilder(index + ". [" + d.getTag() + "]" + unmarked + " " + d.getName());
        }

        information.append(" (by: ").append(dateTimeSystem.format(d.getDate())).append(")");

        storage.write(String.valueOf(information));
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public LocalDateTime getStart() {
        return null;
    }

    @Override
    public LocalDateTime getEnd() {
        return null;
    }
}
