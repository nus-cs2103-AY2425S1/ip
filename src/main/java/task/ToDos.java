package task;

import java.io.IOException;
import java.time.LocalDateTime;
/**
 * Represents a to-do task in a task management system.
 * This class extends the Task class and provides additional functionality specific to to-do tasks.
 */
public class ToDos extends Task {
    /**
     * Constructs a new To-Do task with the specified name.
     *
     * @param name Name of the to-do task.
     * @throws IOException If an I/O error occurs while initializing the to-do task.
     */
    public ToDos(String name) throws IOException {
        super(name, "T");
    }
    /**
     * Adds the to-do task to the task list and updates the storage with the task information.
     *
     * @param t To-do task to be added.
     * @return Message indicating the result of the addition operation.
     * @throws IOException If an I/O error occurs while adding the task or updating the storage.
     */
    public String addTask(ToDos t) throws IOException {
        TaskList.addTasks(t);
        updateTaskList(t);
        return ui.addTodoMessage(t);
    }

    private void updateTaskList(ToDos t) throws IOException {
        String marked = "[X]";
        String unmarked = "[_]";
        int index = TaskList.tasks.size();
        StringBuilder information;
        if (t.getCurrentStatus() == Status.MARKED) {
            information = new StringBuilder(index + ". [" + t.getTag() + "]" + marked + " " + t.getName());
        } else {
            information = new StringBuilder(index + ". [" + t.getTag() + "]" + unmarked + " " + t.getName());
        }

        Task.storage.write(String.valueOf(information));
    }

    @Override
    public LocalDateTime getDate() {
        return null;
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
