package james;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void markTask(int index) {
        tasks.get(index).mark();
    }

    public void unmarkTask(int index) {
        tasks.get(index).unMark();
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).printTask());
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Prints all tasks that contain the specified keyword in their description.
     *
     * This method iterates through all tasks in the task list and prints those
     * whose description contains the provided keyword. Each matching task is
     * printed with a sequential number starting from 1.
     *
     * @param keyWord The keyword to search for in the task descriptions.
     */
    public void printMatchingTasks(String keyWord) {
        int count = 1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).matchKeyWord(keyWord)) {
                System.out.println(count + ". " + tasks.get(i).printTask());
                count++;
            }
        }
    }

}

