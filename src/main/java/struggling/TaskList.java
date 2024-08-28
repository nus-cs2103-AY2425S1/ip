package struggling;
import struggling.task.Deadline;
import struggling.task.Event;
import struggling.task.Task;
import struggling.task.ToDo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 * TaskList object contains the task list and provides
 * operations to add/delete tasks in the list
 */
public class TaskList {

    ArrayList<Task> tasks;

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
        for(String s: input) {
            String[] args = s.split(" \\| ");
            Task task = switch (args[0]) {
                case "T" -> new ToDo(args[2]);
                case "D" -> new Deadline(args[2], LocalDate.parse(args[3]));
                case "E" -> new Event(args[2], args[3], args[4]);
                default -> throw new StrugglingException("Save file corrupted");
            };

            if (Objects.equals(args[1], "1")) {
                task.mark();
            } else if (!Objects.equals(args[1], "0")) {
                throw new StrugglingException("Save file corrupted");
            }

            this.tasks.add(task);
        }
    }

    /**
     * Add a Task into TaskList.
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
     * Returns an ArrayList of formated string
     * representation of each Task in TaskList.
     */
    public ArrayList<String> getTasksString() {
        ArrayList<String> arr = new ArrayList<>();

        for(Task t : tasks) {
            arr.add(t.toString());
        }

        return arr;
    }

    /**
     * Returns an ArrayList of string indicates
     * the state of each Task in TaskList.
     */
    public ArrayList<String> getTasksState() {
        ArrayList<String> arr = new ArrayList<>();

        for(Task t : tasks) {
            arr.add(t.getState());
        }

        return arr;
    }

    /**
     * Returns the current size of the TaskList.
     */
    public int getSize() {
        return this.tasks.size();
    }
}
