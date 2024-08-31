package gopher.task;

import gopher.parser.Parser;
import gopher.storage.TaskManager;
import gopher.ui.UI;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
        TaskManager.saveTasks(tasks);
        UI.printAddTaskMessage(this, task);
    }

    public void delete(int taskNumber) {
        Task task = getTask(taskNumber);
        tasks.remove(taskNumber - 1);
        TaskManager.saveTasks(tasks);
        UI.printDeleteTaskMessage(this, task);
    }

    public TaskList find(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        Pattern keywordPattern = Pattern.compile(keyword,
                Pattern.CASE_INSENSITIVE);

        for (Task task : tasks) {
            Matcher keywordMatcher = keywordPattern.matcher(task.toString());
            if (keywordMatcher.find()) {
                matchedTasks.add(task);
            }
        }

        return new TaskList(matchedTasks);
    }

    public void markAsDone(int taskNumber) {
        Task task = getTask(taskNumber);
        task.markAsDone();
        TaskManager.saveTasks(tasks);
        UI.printMarkAsDoneMessage(task);
    }

    public void markAsUndone(int taskNumber) {
        Task task = getTask(taskNumber);
        task.markAsNotDone();
        TaskManager.saveTasks(tasks);
        UI.printMarkAsUndoneMessage(task);
    }

    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber - 1);
    }

    public int getSize() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            String message = String.format("%d. %s",
                    i,
                    tasks.get(i - 1));
            list.append(message);
            if (i <= tasks.size() - 1) {
                list.append("\n");
            }
        }
        return list.toString();
    }
}
