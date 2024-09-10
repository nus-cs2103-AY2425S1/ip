package echobot.task;

import echobot.exception.TaskNotFoundException;
import echobot.io.Saveable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Saveable {
    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Inserts a task to the task list.
     *
     * @param task  The task to be inserted.
     * @param index The position to be inserted.
     */
    public void addTask(Task task, int index) {
        this.taskList.add(index, task);
    }

    /**
     * Deletes a task by index from the task list.
     *
     * @param index The index of the task to be deleted.
     * @return The task that is deleted.
     * @throws TaskNotFoundException If the index if out of bound.
     */
    public Task deleteTaskByIndex(int index) throws TaskNotFoundException {
        try {
            return this.taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Returns the task list.
     *
     * @return The task list.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Marks a task done by index.
     *
     * @param index The index of the task to be deleted.
     * @return The task that is marked as done.
     * @throws TaskNotFoundException If the index if out of bound.
     */
    public Task markTaskByIndex(int index) throws TaskNotFoundException {
        try {
            Task task = this.taskList.get(index);
            task.markDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Marks a task undone by index.
     *
     * @param index The index of the task to be deleted.
     * @return The task that is marked as undone.
     * @throws TaskNotFoundException If the index if out of bound.
     */
    public Task unmarkTaskByIndex(int index) throws TaskNotFoundException {
        try {
            Task task = this.taskList.get(index);
            task.markUndone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Returns those tasks that occurs on that date.
     * For {@link Event}, it is being returned when the date is between from and to in the Event.
     * For {@link Deadline}, it is being returned when the date is before the date.
     *
     * @param date The date to search.
     * @return A list of tasks that occur on that date.
     */
    public List<Task> getTasksOccurringOn(LocalDate date) {
        List<Task> filteredTaskList = new ArrayList<>();

        for (Task task : this.taskList) {
            if (!(task instanceof ScheduledTask)) {
                continue;
            }
            if (task.isDone()) {
                continue;
            }
            if (!((ScheduledTask) task).isTaskWithinThisDate(date)) {
                continue;
            }
            filteredTaskList.add(task);
        }

        return filteredTaskList;
    }

    /**
     * Finds those tasks which the description contains the keyword.
     *
     * @param keyword The keyword to be searched.
     * @return A list of tasks that contain the keyword as description.
     */
    public List<Task> findTasksByKeyword(String keyword) {
        assert !keyword.isBlank() && !keyword.contains(" ");
        return this.taskList.stream()
                .filter(task -> task.isKeywordContained(keyword))
                .toList();
    }

    /**
     * Returns the size of the list.
     *
     * @return The size of the list.
     */
    public int size() {
        return this.taskList.size();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Task task : this.taskList) {
            output.append(task);
            output.append("\n");
        }
        return output.toString();
    }

    @Override
    public String save() {
        StringBuilder output = new StringBuilder();
        for (Saveable task : this.taskList) {
            output.append(task.save());
            output.append("\n");
        }
        return output.toString();
    }
}
