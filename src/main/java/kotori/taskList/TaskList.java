package kotori.taskList;

import java.util.ArrayList;
import java.util.Objects;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    public TaskList () {

    }

    public int size() {
        return list.size();
    }

     public Task get(int i) {
        return list.get(i);
     }

     public boolean add(Task t) {
        return list.add(t);
     }

     public Task remove(int i) {
        return list.remove(i);
     }

     public void mark(int i) throws IncorrectStateException {
        list.get(i).mark();
     }

     public void unmark(int i) throws IncorrectStateException {
        list.get(i).unmark();
     }

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

    public TaskList findAll(String description) {
        TaskList result = new TaskList();
        for (Task t : list) {
            if (t.hasDescription(description)) {
                result.add(t);
            }
        }
        return result;
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
