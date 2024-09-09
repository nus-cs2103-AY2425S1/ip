package kotori.taskList;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * This class represent a list of task
 * */


public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {

    }

    /**
     * returns the size of the list.
     *
     * @return size of list.
     * */

    public int size() {
        return taskList.size();
    }

    public Task get(int i) {
        return taskList.get(i);
    }
    /**
     * Adds a task into the list.
     *
     * @param t the task to be added.
     * @return if the action is success.
     * */
    public boolean add(Task t) {
        return taskList.add(t);
    }
    /**
     * Removes a task into the list.
     *
     * @param i index of the target.
     * @return the removed task.
     * */

    public Task remove(int i) {
        return taskList.remove(i);
    }
    /**
     * Marks a task into the list.
     *
     * @param i the index of target.
     * */

    public void mark(int i) throws IncorrectStateException {
        taskList.get(i).mark();
    }
    /**
     * Unmarks a task into the list.
     *
     * @param i the index of target.
     * */

    public void unmark(int i) throws IncorrectStateException {
        taskList.get(i).unmark();
    }
    /**
      * checks if a list is empty.
      *
      * @return if the list is empty.
      * */

    public boolean isEmpty() {
        return taskList.isEmpty();
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TaskList) {
            return this.taskList.equals(((TaskList) obj).taskList);
        } else {
            return false;
        }
    }

    /**
     * find a sublist that each element has the description
     *
     * @param description the description.
     * @return the sublist found
     * */

    public TaskList findAll(String description) {
        TaskList result = new TaskList();
        for (Task t : taskList) {
            if (t.hasDescription(description)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Sort the list in urgency order
     * */
    public void sort() {
        taskList = taskList.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
    }
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            result += String.format("%s %s", i, taskList.get(i));
        }
        return result;
    }


}
