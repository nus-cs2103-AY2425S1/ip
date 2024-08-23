package taskManager;
import task.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private int taskIdCounter;

    public TaskManager() {
        tasks = new ArrayList<>();
        taskIdCounter = 1;
    }

    public Task addTodo(String description) {
        Task task = new Todo(taskIdCounter++, description);
        tasks.add(task);
        return task;
    }

    public Task addDeadline(String description, String by) {
        Task task = new Deadline(taskIdCounter++, description, by);
        tasks.add(task);
        return task;
    }

    public Task addEvent(String description, String from, String to) {
        Task task = new Event(taskIdCounter++, description, from, to);
        tasks.add(task);
        return task;
    }

    public List<Task> listTasks() {
        return new ArrayList<>(tasks);
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks.");
        } else {
            System.out.println("Here are the tasks in your list:");
            List<Task> taskList = listTasks();
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + "." + taskList.get(i).toString());
            }
        }
    }

    public Task deleteTask(int listPosition) {
        int index = listPosition - 1;
        if (index >= 0 && index < tasks.size()) {
            return tasks.remove(index);
        }

        return null;
    }

    public Task markAsDone(int listPosition) {
       Task temp = tasks.get(listPosition - 1);
       temp.setCompleted(true);
       return temp;
    }

    public Task unmarkAsDone(int listPosition) {
        Task temp = tasks.get(listPosition - 1);
        temp.setCompleted(false);
        return temp;
    }


    public boolean isvalidIndex(int listPosition) {
        if (tasks.isEmpty()) {
            return false;
        }

        return listPosition > 0 && listPosition <= tasks.size();
    }

    public int getSize() {
        return tasks.size();
    }







}
