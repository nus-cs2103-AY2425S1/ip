package task;

import java.io.IOException;
import java.time.LocalDateTime;
/**
 * Represents an event task in a task management system.
 * An event task is a type of task that has a specific start and end date and time.
 * This class extends the Task class and provides additional functionality specific to event tasks.
 */
public class Events extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    /**
     * Constructs a new Event task with the specified name, start time, and end time.
     *
     * @param name Name of the event task.
     * @param start Start date and time of the event.
     * @param end End date and time of the event.
     * @throws IOException If an I/O error occurs while initializing the event task.
     */
    public Events(String name, LocalDateTime start, LocalDateTime end) throws IOException {
        super(name, "E");
        this.start = start;
        this.end = end;
    }

    /**
     * Adds the event task to the task list and updates the storage with the task information.
     *
     * @param e Event task to be added.
     * @return Message indicating the result of the addition operation.
     * @throws IOException If an I/O error occurs while adding the task or updating the storage.
     */
    public String addTask(Events e) throws IOException {
        TaskList.addTasks(e);
        updateTasklist(e);
        return ui.addEventMessage(e, this.start, this.end);
    }

    private void updateTasklist(Events e) throws IOException {
        String marked = "[X]";
        String unmarked = "[_]";
        int index = TaskList.tasks.size();
        StringBuilder information;
        if (e.getCurrentStatus() == Status.MARKED) {
            information = new StringBuilder(index
                    + ". [" + e.getTag() + "]" + marked + " " + e.getName());
        } else {
            information = new StringBuilder(index
                    + ". [" + e.getTag() + "]" + unmarked + " " + e.getName());
        }

        information.append(" (from: ").append(dateTimeSystem.formatLocalTimeDate(e.getStart())).append(" to: ").append(dateTimeSystem.formatLocalTimeDate(e.getEnd())).append(")");
        storage.write(String.valueOf(information));
    }

    @Override
    public LocalDateTime getDate() {
        return null;
    }

    @Override
    public LocalDateTime getStart() {
        return start;
    }

    @Override
    public LocalDateTime getEnd() {
        return end;
    }
}
