package kitty;

import kitty.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<Task>(100);
    private int numOfTasksDone;
    private int numOfTasksNotDone;

    public TaskList() {
        numOfTasksDone = 0;
        numOfTasksNotDone = 0;
    }

    public int addTask(Task task) {
        boolean isAdded = tasks.add(task);
        if (!isAdded) {
            return -1;
        }

        numOfTasksNotDone++;
        return tasks.size();
    }

    public String deleteTask(int index) throws IndexOutOfBoundsException {
        String str = "";
        Task tmp = tasks.get(index - 1);
        String note = tmp.toString();
        tasks.remove(index - 1);
        if (tmp.isDone()) {
            numOfTasksDone--;
        } else {
            numOfTasksNotDone--;
        }

        str = String.format("  " + note + "\nNow you have " + tasks.size() + " tasks in the list.");
        return str;
    }

    public Task markDone(int index) throws IndexOutOfBoundsException {
        Task tmp = tasks.get(index - 1);
        tmp.mark();

        numOfTasksDone++;
        numOfTasksNotDone--;
        return tmp;
    }

    public Task markUndone(int index) throws IndexOutOfBoundsException {
        Task tmp = tasks.get(index - 1);
        tmp.unmark();

        numOfTasksNotDone++;
        numOfTasksDone--;
        return tmp;
    }

    public Task editTag(int index, String tag) throws IndexOutOfBoundsException {
        Task tmp = tasks.get(index - 1);
        tmp.updateTag(tag);

        return tmp;
    }

    public String findTask(String keyword) {
        StringBuilder str = new StringBuilder();
        tasks.stream().filter(task -> task.containsKeyword(keyword))
                .forEach(task -> str.append(task.toString()));
        return str.toString();
    }

    public String getData() {
        StringBuilder str = new StringBuilder();
        for (Task task: tasks) {
            str.append(task.getTaskData());
        }
        return str.toString();
    }

    public String list() {
        int count = 1;
        Task[] tmp = new Task[0];
        StringBuilder message = new StringBuilder();
        for (Task item: tasks.toArray(tmp)) {
            message.append(count++).append(".").append(item).append("\n");
        }
        System.out.println(message.toString());
        return message.toString();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
