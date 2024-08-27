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

    public TaskList() {
        this.strandList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> strandList) {
        this.strandList = strandList;
    }

    /**
     * Adds a new task to the strandList based on the input command.
     *
     * @param newTask Task to be added.
     */
    public void addTask(Task newTask) {
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
     * @param index Index of task to be marked or unmarked.
     * @param mark  Whether task is to be marked or unmarked.
     */
    public Task mark(Integer index, Boolean mark) throws StrandException {
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

    public Integer getNumOfTasks() {
        return this.strandList.size();
    }

    @Override
    public String toString() {
        return this.strandList.stream()
                .map((x) -> (this.strandList.indexOf(x) + 1) + "." + x.toString() + "\n")
                .reduce((a, b) -> a + b).orElse("");
    }

    public String toFile() {
        return strandList.stream().map((x) -> x.getFile() + "\n").reduce((a, b) -> a + b).orElse("");
    }
}
