package bibi.task;

import java.util.ArrayList;
import bibi.Command;

/**
 * Represents the collection of tasks and its subtypes.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task with the given index from the list.
     *
     * @param index The index of the task. Starts from 1.
     * @return The task that was removed from the list.
     */
    public Task removeFromTaskList(int index) {
        if (index > tasks.size() || index <= 0) {
            return null;
        } else {
            return tasks.remove(index - 1);
        }
    }

    public void addToTaskList(Task t) {
        tasks.add(t);
    }

    /**
     * Returns number of tasks that match the given pattern.
     *
     * @param pattern The pattern to match with task descriptions.
     * @param sb The StringBuilder to append to.
     * @return int with count of tasks that match the given pattern.
     */
    public int findTaskByPattern(String pattern, StringBuilder sb) {
        int count = 0;
        for (int i = 0; i < getTaskCount(); i++) {
            String[] words = pattern.split(" ");
            for (int j = 0; j < words.length; j++) {
                if (getTask(i).getDescription().contains(words[j])) {
                    sb.append(String.format("%d.%s%n", i + 1, getTask(i)));
                    count++;
                }
            }
        }
        return count;
    }
}
