package task;

import exceptions.HandsomeException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;
    private static Task lastDeletedTask;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String addTask(Task task) {
        assert task != null : "Well... Does it really make sense to add nothing?";
        tasks.add(task);
        return "Looking productive! I have added: \n" + task +
                ("\nSeems like the task count is " + tasks.size() + ", don't overwork yourself wookie?");
    }

    public String printList() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hey, here are the tasks you have!\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    public String markDone(int taskNum) {
        tasks.get(taskNum).markAsDone();
        return "Congrats, you finally finished this task!\n" + (tasks.get(taskNum).toString());
    }

    public String markUndone(int taskNum) {
        tasks.get(taskNum).markAsUndone();
        return "Hey, the task is not done but time to stop procrastinating?" + (tasks.get(taskNum).toString());
    }

    public String deleteCheck(int taskNum) throws HandsomeException {
        if (taskNum > tasks.size()) {
            throw new HandsomeException("Hey, that task simply... does not exist.");
        }
        // temporarily store task to let user know what is deleted
        Task removedTask = tasks.remove(taskNum);
        lastDeletedTask = removedTask;
        return ("Well... I have removed the task: \n" + removedTask)
                + ("\nNow you have " + tasks.size() + " count of tasks left to do");
    }

    public String addTaskAtIndex(int taskNum) {
        tasks.add(taskNum, lastDeletedTask);
        return "Hey, I got you! " + lastDeletedTask.toString() + " has been added!";
    }

    /**
     * Finds the corresponding tasks based on the keyword the user inputs.
     *
     * @param keyword word that the user wants to find.
     * @return a list of those tasks with the keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }
}
