package buddybot;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> myList;

    public TaskList() {
        myList = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> taskList) {
        this.myList = taskList;
    }

    public void addTask(Task task) {
        myList.add(task);
    }

    /**
     *
     * @return
     */
    public int size() {
        return myList.size();
    }

    /**
     *
     * @param i
     * @return
     */
    public Task get(int i) {
        return myList.get(i-1);
    }

    public void add(Task t) {
        myList.add(t);
    }

    public void delete(int i) {
        myList.remove(i);
    }

    public boolean isEmpty() {
        return myList.isEmpty();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < myList.size(); i++) {
            if (myList.get(i) != null) {
                result.append(myList.get(i).toString()).append("\n");
            }
        }
        return result.toString();
    }

    public String toFile() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < myList.size(); i++) {
            if (myList.get(i) != null) {
                result.append(myList.get(i).toFile()).append("\n");
            }
        }
        return result.toString();
    }
}
