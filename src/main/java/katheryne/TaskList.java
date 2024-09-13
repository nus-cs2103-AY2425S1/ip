package katheryne;

import java.util.ArrayList;
import java.util.Set;


/**
 * TaskList is used to store and manage tasks
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(TaskList l) {
        this.taskList = l.getTaskList();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void mark(int id) {
        Task t = taskList.get(id);
        t.mark();
    }

    public void unmark(int id) {
        Task t = taskList.get(id);
        t.unmark();
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public void deleteTask(int id) {
        taskList.remove(id);
    }

    public Task getTask(int id) {
        return taskList.get(id);
    }

    public TaskList findTask(String str) {
        TaskList searchResult = new TaskList();
        for (Task t : taskList) {
            String description = t.getDescription();
            if (description.contains(str)) {
                searchResult.addTask(t);
            }
        }
        return searchResult;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            String item = index + ". " + taskList.get(i).toString();
            if (i != 0) {
                output = output + '\n' + item;
            } else {
                output = output + item;
            }
        }
        return output;
    }

    public boolean hasDuplicates(Task t) {
        for (Task task : taskList) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }
}
