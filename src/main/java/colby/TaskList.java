package colby;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    public void markTaskAsUndone(int index) {
        tasks.get(index).markAsUndone();
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks in the main list which include words that match the keyword
     * @param keyword word that is searched for in the tasks' description
     * @return List with all tasks which have the keyword in their description
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(tasks.get(i));
            }
        }
        return matchingTasks;
    }

    /**
     * Prints out the list of tasks with words matching the keyword
     * @param keyword word that the method will print tasks which include the word based on
     */
    public void printMatchingTasks(String keyword) {
        List<Task> matchingTasks = findTasks(keyword);

        System.out.println("Here are the tasks matching you search:");

        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + ". " + matchingTasks.get(i));
        }
    }

}
