import java.util.*;

public class TaskList {

    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task item) {
        this.list.add(item);
    }

    public String delete(int index) {
        String taskToBeRemoved = list.get(index - 1).toString();
        this.list.remove(index - 1);
        return taskToBeRemoved;
    }

    public int getLength() {
        return this.list.size();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(i+1 + "." + list.get(i).toString());
        }
    }

    public String markAsDone(int index) {
        list.get(index).markAsDone();
        return list.get(index).toString();
    }

    public String markAsNotDone(int index) {
        list.get(index).markAsNotDone();
        return list.get(index).toString();
    }
}
