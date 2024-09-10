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

    ArrayList<Task> todoList;

    /**
     * Initializes task list with empty array list
     */
    public TaskList() {
        this.todoList = new ArrayList<>();
    }

    /**
     * Initializes the saved task list.
     *
     * @param sc Scanner file containing contents of saved task list.
     */
    public TaskList(Scanner sc) {
        this.todoList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            String[] split = task.split(",");
            String taskType = split[0];
            Boolean isDone = split[1].equals("X");
            String desc = split[2];
            if (taskType.equals("T")) {
                Todo todo = new Todo(desc);
                this.todoList.add(todo);
                if (isDone) {
                    todo.mark();
                }
            } else if (taskType.equals("D")) {
                String by = split[3];

                Deadline deadline = new Deadline(desc, by);
                this.todoList.add(deadline);
                if (isDone) {
                    deadline.mark();
                }
            } else {
                String from = split[3];
                String to = split[4];
                Event event = new Event(desc, from, to);
                this.todoList.add(event);
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

        this.todoList.add(task);
    }

    /**
     * Removes task at given index of task list.
     *
     * @param index Index of task to be removed in task list.
     * @return Task that was removed.
     */
    public Task remove(int index) {

        return this.todoList.remove(index);
    }

    /**
     * Retrieves task at given index of task list.
     *
     * @param index Index of task to be retrieved in task list.
     * @return Task that was retrieved.
     */
    public Task get(int index) {
        return this.todoList.get(index);
    }

    /**
     * Returns size of task list.
     *
     * @return Number of elements in task list.
     */
    public int size() {
        return this.todoList.size();
    }

    @Override
    public String toString() {
        return this.todoList.toString();
    }

}
