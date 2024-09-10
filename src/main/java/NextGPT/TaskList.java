package nextgpt;

import java.util.ArrayList;
import java.util.Scanner;

import nextgpt.task.Deadline;
import nextgpt.task.Event;
import nextgpt.task.Task;
import nextgpt.task.Todo;

/**
 * Class to hold tasks in a arraylist.
 */
public class TaskList {

    ArrayList<Task> todo_list;

    public TaskList(){

        this.todo_list = new ArrayList<>();
    }

    /**
     * Initializes the saved task list.
     *
     * @param sc Scanner file containing contents of saved task list.
     */
    public TaskList(Scanner sc) {
        this.todo_list = new ArrayList<>();
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            String[] split = task.split(",");
            String taskType = split[0];
            Boolean isDone = split[1].equals("X");
            String desc = split[2];
            if (taskType.equals("T")) {
                Todo todo = new Todo(desc);
                this.todo_list.add(todo);
                if (isDone) {
                    todo.mark();
                }
            } else if (taskType.equals("D")) {
                String by = split[3];

                Deadline deadline = new Deadline(desc, by);
                this.todo_list.add(deadline);
                if (isDone) {
                    deadline.mark();
                }
            } else {
                String from = split[3];
                String to = split[4];
                Event event = new Event(desc, from, to);
                this.todo_list.add(event);
                if (isDone) {
                    event.mark();
                }
            }

        }
    }

    /**
     * Adds task to task list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {

        this.todo_list.add(task);
    }

    /**
     * Removes task at given index of task list.
     *
     * @param index Index of task to be removed in task list.
     * @return Task that was removed.
     */
    public Task remove(int index) {

        return this.todo_list.remove(index);
    }

    /**
     * Retrieves task at given index of task list.
     *
     * @param index Index of task to be retrieved in task list.
     * @return Task that was retrieved.
     */
    public Task get(int index) {

        return this.todo_list.get(index);
    }

    /**
     * Returns size of task list.
     *
     * @return Number of elements in task list.
     */
    public int size() {
        return this.todo_list.size();
    }

    @Override
    public String toString() {

        return this.todo_list.toString();
    }

}
