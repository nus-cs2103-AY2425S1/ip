package kotori.taskList;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList () {

    }

    public int size() {
        return taskList.size();
    }

     public Task get(int i) {
        return taskList.get(i);
     }

     public boolean add(Task t) {
        return taskList.add(t);
     }

     public Task remove(int i) {
        return taskList.remove(i);
     }

     public void mark(int i) throws IncorrectStateException {
        taskList.get(i).mark();
     }

     public void unmark(int i) throws IncorrectStateException {
        taskList.get(i).unmark();
     }

     public boolean isEmpty() {
        return taskList.isEmpty();
     }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TaskList) {
            return this.taskList.equals(((TaskList)obj).taskList);
        } else {
            return false;
        }
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
