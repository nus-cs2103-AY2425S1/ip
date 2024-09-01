package genji.task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task t) {
        taskList.add(t);
    }

    public void delete(int i) {
        taskList.remove(i);
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public ArrayList<Task> getList() {
        return taskList;
    }

    public int size() {
        return taskList.size();
    }

    public void mark(int i) {
        taskList.get(i).mark();
    }

    public void unmark(int i) {
        taskList.get(i).unmark();
    }

    public void showList() {
        if (taskList.size() == 0) {
            System.out.println("No task on list");
        } else {
            int index = 1;
            for (Task task : taskList) {
                System.out.println(String.format("%d. ", index) + task);
                index++;
            }
        }
    }

    public void checkDate(String date) {
        for (Task t : taskList) {
            if (t instanceof Deadline) {
                Deadline temp  = (Deadline) t;
                if (temp.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(date)) {
                    System.out.println(t);
                }
            } else if (t instanceof Event) {
                Event temp = (Event) t;
                if (temp.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(date)) {
                    System.out.println(t);
                }
            }
        }
    }
}
