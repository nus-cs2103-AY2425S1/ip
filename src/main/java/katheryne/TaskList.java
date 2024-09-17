package katheryne;

import java.util.ArrayList;


/**
 * TaskList is used to store and manage tasks
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Marks a task as done
     * @param id
     */
    public void mark(int id) {
        Task t = taskList.get(id);
        t.mark();
    }

    /**
     * Unmarks a task
     * @param id
     */
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

    /**
     * Finds task with specified description
     * @param str description to be searched in the task list
     * @return a tasklist of tasks containing the given description
     */
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

    /**
     * Compares the given task with other in the task list for duplication detection
     * @param t given task
     * @return boolean value of whether given task is a duplicate of other tasks
     */
    public boolean hasDuplicates(Task t) {
        for (Task task : taskList) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }
}
