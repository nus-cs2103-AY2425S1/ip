package FRIDAY;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> list) {
        this.taskList = new ArrayList<>();
        taskList.addAll(list);
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }

    public String displayTasks() {
        if(taskList.isEmpty()) {
            return "You currently have no tasks in your list";
        }
        StringBuilder sb = new StringBuilder();
        taskList.forEach((task) -> {
            sb.append(taskList.indexOf(task) + 1).append(". ").append(task).append("\n");
        });
        return sb.toString();
    }

    public void markTask(int index) {
        taskList.get(index).check();
    }

    public void unMarkTask(int index) {
        taskList.get(index).uncheck();
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public int numTasks() {
        return taskList.size();
    }

    public Task getTaskAt(int index) {
        return taskList.get(index);
    }

    /**
     * This method searches the tasklist and returns an ArrayList of Tasks that have descriptions which contain the keyword
     * @param keyword the keyword that the user tries to search for
     * @return ArrayList of tasks that have descriptions containing the keyword
     */
    public ArrayList<Task> search(String keyword) {
        ArrayList<Task> list = new ArrayList<>();
        taskList.forEach((task) -> {
          if(task.containsKeyword(keyword)) {
              list.add(task);
          }
        });
        return list;
    }
}
