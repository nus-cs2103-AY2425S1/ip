package struggling;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import struggling.task.Deadline;
import struggling.task.Event;
import struggling.task.Task;
import struggling.task.ToDo;

/**
 * TaskList object contains the task list and provides
 * operations to add/delete tasks in the list
 */
public class TaskList {

    private static final int TYPE_INDEX = 0;
    private static final int PRIORITY_INDEX = 1;
    private static final int IS_DONE_INDEX = 2;
    private static final int DESCRIPTION_INDEX = 3;
    private static final int FIRST_DATE_INDEX = 4;
    private static final int SECOND_DATE_INDEX = 5;

    private final ArrayList<Task> tasks;

    /**
     * Initializes TaskList with an empty ArrayList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initializes TaskList with input data.
     *
     * @param input Input data to populate the TaskList.
     */
    public TaskList(ArrayList<String> input) {


        tasks = new ArrayList<>();
        for (String s : input) {
            String[] args = s.split(" \\| ");

            Task task;

            String taskPriority = args[PRIORITY_INDEX];
            String taskType = args[TYPE_INDEX];
            String taskIsDone = args[IS_DONE_INDEX];
            String taskDescription = args[DESCRIPTION_INDEX];

            if (Objects.equals(taskType, "T")) {
                task = new ToDo(taskDescription);
            } else if (Objects.equals(taskType, "D")) {
                String taskFirstDate = args[FIRST_DATE_INDEX];
                task = new Deadline(taskDescription, LocalDate.parse(taskFirstDate));
            } else if (Objects.equals(taskType, "E")) {
                String taskFirstDate = args[FIRST_DATE_INDEX];
                String taskSecondDate = args[SECOND_DATE_INDEX];
                task = new Event(taskDescription, taskFirstDate, taskSecondDate);
            } else {
                throw new StrugglingException("Save file corrupted");
            }

            if (Objects.equals(taskPriority, "1")) {
                task.setPriorityHigh();
            } else if (!Objects.equals(taskPriority, "0")) {
                throw new StrugglingException("Save file corrupted");
            }

            if (Objects.equals(taskIsDone, "1")) {
                task.mark();
            } else if (!Objects.equals(taskIsDone, "0")) {
                throw new StrugglingException("Save file corrupted");
            }

            this.tasks.add(task);
        }
    }

    /**
     * Adds a Task into TaskList.
     *
     * @param task Task to be added to TaskList.
     * @return The Task added.
     */
    public Task addTask(Task task) {
        this.tasks.add(task);
        return task;
    }

    /**
     * Removes a Task from TaskList.
     *
     * @param i Index of the Task in TaskList.
     * @return The Task removed.
     */
    public Task deleteTask(int i) {
        Task t = this.tasks.remove(i);
        return t;
    }

    /**
     * Sets the isDone property of a Task in TaskList to true.
     *
     * @param i Index of the Task in TaskList.
     * @return The Task marked.
     */
    public Task markTask(int i) {
        Task t = this.tasks.get(i);
        t.mark();
        return t;
    }

    /**
     * Sets the isDone property of a Task in TaskList to false.
     *
     * @param i Index of the Task in TaskList.
     * @return The Task unmarked.
     */
    public Task unmarkTask(int i) {
        Task t = this.tasks.get(i);
        t.unmark();
        return t;
    }

    /**
     * Sets the Priority property of a Task in TaskList to HIGH.
     *
     * @param i Index of the Task in TaskList.
     * @return The Task with high priority.
     */
    public Task setTaskPriorityHigh(int i) {
        Task t = this.tasks.get(i);
        t.setPriorityHigh();
        return t;
    }

    /**
     * Sets the Priority property of a Task in TaskList to LOW.
     *
     * @param i Index of the Task in TaskList.
     * @return The Task with low priority.
     */
    public Task setTaskPriorityLow(int i) {
        Task t = this.tasks.get(i);
        t.setPriorityLow();
        return t;
    }

    /**
     * Returns an ArrayList of formated string
     * representation of each Task in TaskList.
     */
    public ArrayList<String> getTasksString() {
        return tasks
                .stream()
                .map(Task::toString)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns an ArrayList of string indicates
     * the state of each Task in TaskList.
     */
    public ArrayList<String> getTasksState() {
        return tasks
                .stream()
                .map(Task::getState)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns the current size of the TaskList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns a list of string representation of tasks which
     * descriptions contain the keyword.
     *
     * @param keyword The string to find in task description.
     */
    public ArrayList<String> findTask(String keyword) {
        return tasks
                .stream()
                .filter(task -> task
                        .getDescription()
                        .contains(keyword))
                .map(Task::toString)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
