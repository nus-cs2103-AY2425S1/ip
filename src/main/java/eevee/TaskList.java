package eevee;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int taskNumber) throws EeveeException {
        if (taskNumber > tasks.size() || taskNumber < 1) {
            throw new EeveeException("No task under the given task number. "
                    + "Did you type the wrong number?");
        }
        return tasks.get(taskNumber - 1);
    }
    public void addTask(Task t) {
        tasks.add(t);
    }

    public void removeTask(int taskNumber) throws EeveeException {
        if (taskNumber > tasks.size() || taskNumber < 1) {
            throw new EeveeException("No task under the given task number. "
                    + "Did you type the wrong number?");
        }
        tasks.remove(taskNumber - 1);
    }

    public int getSize() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Finds tasks that have descriptions matching the given keyword.
     *
     * @param keyword The String that is to be found in task descriptions.
     * @return The ArrayList of tasks that have matching descriptions.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(t);
            }
        }
        return result;
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet! Start adding tasks :)");
            return;
        }
        System.out.println("Here are your tasks:");
        tasks.forEach((task) -> System.out.println((tasks.indexOf(task) + 1) + ". " + task));
    }
}
