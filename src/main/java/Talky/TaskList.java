package Talky;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public String toListFormat() {
        String result = "";
        int rank = 1;
        for (Task task : taskList) {
            result += rank + "." + task.toString() + "\n";
            rank++;
        }
        return result;
    }

    public String toSaveFormat() {
        String saveFormat = "";
        for (Task task : taskList) {
            saveFormat += (task.toSaveFormat() + "\n");
        }
        return saveFormat;
    }

    public String markTask(int index, boolean isMarked) {
        Task changedTask = taskList.get(index);
        changedTask.setMark(isMarked);
        return changedTask.getName();
    }

    public void addToDo(String name) {
        taskList.add(new ToDo(name));
    }

    public void addDeadline(String name, LocalDateTime by) {
        taskList.add(new Deadline(name, by));
    }

    public void addEvent(String name, LocalDateTime from, LocalDateTime to) {
        taskList.add(new Event(name, from, to));
    }

    public String deleteTask(int index) {
        Task changedTask = taskList.get(index);
        taskList.remove(index);
        return changedTask.getName();
    }

    public TaskList find(String keyword) {
        TaskList validTasks = new TaskList();
        for (Task task: taskList) {
            String taskName = task.getName();
            if (taskName.contains(keyword)) {
                validTasks.addTask(task);
            }
        }
        return validTasks;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }
}
