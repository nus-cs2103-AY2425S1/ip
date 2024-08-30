package kitty;

import kitty.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> tasks = new ArrayList<Task>(100);

    public int addTask(Task task) {
        return tasks.add(task)? tasks.size() : -1;
    }

    public String deleteTask(int index) throws IndexOutOfBoundsException{
        String str = "";
        Task tmp = tasks.get(index - 1);
        String note = tmp.toString();
        tasks.remove(index - 1);
        str = String.format("  " + note + "\nNow you have " + tasks.size() + " tasks in the list.");
        return str;
    }

    public Task markDone(int index) throws IndexOutOfBoundsException{
        Task tmp = tasks.get(index - 1);
        tmp.mark();
        return tmp;
    }

    public Task markUndone(int index) throws IndexOutOfBoundsException{
        Task tmp = tasks.get(index - 1);
        tmp.unmark();
        return tmp;
    }

    public String findTask(String keyword) {
        StringBuilder str = new StringBuilder();
        tasks.stream().filter(task -> task.containsKeyword(keyword))
                .forEach(task -> str.append(task.getTaskData()));
        return str.toString();
    }

    public String getData() {
        StringBuilder str = new StringBuilder();
        for (Task task: tasks) {
            str.append(task.getTaskData());
        }
        return str.toString();
    }

    public void list() {
        int count = 1;
        Task[] tmp = new Task[0];
        for (Task item: tasks.toArray(tmp)) {
            System.out.println(count++ + "." + item);
        }
    }
}
