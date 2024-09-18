package assistinator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents list of task.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to task list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes specified task.
     * @param index Index of task.
     * @throws AssitinatorException If index is invalid.
     */
    public void deleteTask(int index) throws AssitinatorException {
        if (index < 0 || index >= tasks.size()) {
            throw new AssitinatorException("Invalid task index");
        }
        tasks.remove(index);
    }

    /**
     * Marks specified task as done or undone.
     * @param index Task index.
     * @param isDone Whether task is done or not done.
     * @throws AssitinatorException If task index is invalid.
     */
    public void markTask(int index, boolean isDone) throws AssitinatorException {
        if (index < 0 || index >= tasks.size()) {
            throw new AssitinatorException("Invalid task index");
        }
        if (isDone) {
            tasks.get(index).markAsDone();
        } else {
            tasks.get(index).markAsUndone();
        }
    }

    /**
     * Returns formatted string for task list for list command.
     * @return Formatted string.
     */
    public String listTasks() {
        return IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + "." + tasks.get(i).toString())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Filters and generates output string.
     * @param keyword Search keyword.
     * @return Filtered string.
     */
    public String filterTasks(String keyword) {
        return IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).contains(keyword))
                .mapToObj(i -> (i + 1) + "." + tasks.get(i).toString())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Returns task list size.
     * @return Task list size.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns task list.
     * @return Task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Checks if the new task clashes with any existing tasks.
     *
     * @param newTask The task to check for clashes.
     * @return True if there's a clash, false otherwise.
     */
    public Event hasTimeClash(Task newTask) {
        if (newTask instanceof Todo || newTask instanceof Deadline) {
            return null; // TodoTasks have no time restrictions, so they can't clash
        }

        LocalDateTime newTaskStart = null;
        LocalDateTime newTaskEnd = null;

        Event eventTask = (Event) newTask;
        newTaskStart = eventTask.getStartTime();
        newTaskEnd = eventTask.getEndTime();

        for (Task existingTask : tasks) {
            if (existingTask instanceof Event) {
                Event existingEvent = (Event) existingTask;
                if (tasksOverlap(newTaskStart, newTaskEnd, existingEvent.getStartTime(), existingEvent.getEndTime())) {
                    return existingEvent;
                }
            }
        }
        return null;
    }

    private boolean tasksOverlap(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {
        return start1.isBefore(end2) && start2.isBefore(end1);
    }
}
