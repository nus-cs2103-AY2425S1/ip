package kotori.taskList;

import java.util.ArrayList;

/**
 * This class represent a list of task
 * */

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    public TaskList () {

    }

    /**
     * returns the size of the list.
     *
     * @return size of list.
     * */

    public int size() {
        return list.size();
    }

     public Task get(int i) {
        return list.get(i);
     }

     /**
      * Adds a task into the list.
      *
      * @param t the task to be added.
      * @return if the action is success.
      * */

     public boolean add(Task t) {
        return list.add(t);
     }

    /**
     * Removes a task into the list.
     *
     * @param i index of the target.
     * @return the removed task.
     * */

     public Task remove(int i) {
        return list.remove(i);
     }

    /**
     * Marks a task into the list.
     *
     * @param i the index of target.
     * */

     public void mark(int i) throws IncorrectStateException {
        list.get(i).mark();
     }

    /**
     * Unmarks a task into the list.
     *
     * @param i the index of target.
     * */

     public void unmark(int i) throws IncorrectStateException {
        list.get(i).unmark();
     }

     /**
      * checks if a list is empty.
      *
      * @return if the list is empty.
      * */

     public boolean isEmpty() {
        return list.isEmpty();
     }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TaskList) {
            return this.list.equals(((TaskList)obj).list);
        } else {
            return false;
        }
    }

    @Override
     public String toString() {
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result += String.format("%s %s", i, list.get(i));
        }
        return result;
     }


}
