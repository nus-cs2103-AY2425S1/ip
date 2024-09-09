package strand;

import java.util.ArrayList;

import strand.exception.StrandException;
import strand.exception.StrandWrongIndexException;
import strand.task.Task;

/**
 * The {@code TaskList} class interacts keeps track of the list of tasks and
 * edits it according to the commands.
 * <p>
 * This class may throw exceptions if the index of the task is invalid.
 * </p>
 */
public class TaskList {
    private final ArrayList<Task> strandList;

    /**
     * Constructs an empty {@code TaskList} with no initial tasks.
     */
    public TaskList() {
        this.strandList = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} initialized with an existing list of tasks.
     *
     * @param strandList The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> strandList) {
        this.strandList = strandList;
        assert strandList != null : "TaskList cannot be initialized with a null list";
    }

    /**
     * Adds a new task to the list of tasks.
     *
     * @param newTask The task to be added to the list.
     */
    public void addTask(Task newTask) {
        assert newTask != null : "Cannot add a null task";
        strandList.add(newTask);
    }

    /**
     * Delete task from the strandList
     *
     * @param index Index of task to be removed.
     * @throws StrandException If the task description or necessary parameters are missing or incorrect.
     */
    public Task deleteTask(Integer index) throws StrandException {
        if (index > this.strandList.size() || index < 1) {
            throw new StrandWrongIndexException(this.strandList.size());
        }
        Task t = this.strandList.get(index - 1);
        this.strandList.remove(index - 1);
        return t;
    }

    /**
     * Mark or unmark a task from the strandList
     * @param index Index of task to be marked or unmarked.
     * @param mark  Whether task is to be marked or unmarked.
     */
    public Task mark(Integer index, Boolean mark) throws StrandException {
        assert index != null : "Index cannot be null";
        if (index > strandList.size() || index < 1) {
            throw new StrandWrongIndexException(strandList.size());
        }
        Task task = strandList.get(index - 1);
        if (mark) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
        return task;
    }

    /**
     * Return number of tasks from the strandList
     */
    public Integer getNumOfTasks() {
        return this.strandList.size();
    }

    @Override
    public String toString() {
        return this.strandList.stream()
                .map((x) -> (this.strandList.indexOf(x) + 1) + "." + x.toString() + "\n")
                .reduce((a, b) -> a + b).orElse("");
    }

    /**
     * Return file representation of strandList to be saved in file
     */
    public String convertToFileFormat() {
        return strandList.stream().map((x) -> x.convertToFileFormat() + "\n").reduce((a, b) -> a + b).orElse("");
    }

    /**
     * Shows all tasks with the segment in their description
     * @param segment Segment that all shown tasks should contain in their description.
     */
    public String getFoundTasks(String segment) {
        return strandList.stream().filter((x) -> x.containsSegment(segment))
                .map((x) -> (this.strandList.indexOf(x) + 1) + "." + x + "\n")
                .reduce((a, b) -> a + b).orElse("");
    }
}
