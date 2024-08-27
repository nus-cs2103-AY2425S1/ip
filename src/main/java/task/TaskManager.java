package task;

import exception.IllegalTaskArgumentException;
import exception.IllegalTaskNumberException;
import exception.IllegalTaskTypeException;
import parser.TaskFactory;
import storage.DbManager;

import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> taskList;
    private final DbManager db;
    public TaskManager() {
        this.db = new DbManager();
        this.taskList = db.getTasks();
    }

    public int getTaskCount() {
        return this.taskList.size();
    }

    public Task addTask(String taskTypeStr, String taskArgs) throws IllegalTaskTypeException, IllegalTaskArgumentException {
        Task task = TaskFactory.userInputToTask(taskTypeStr, taskArgs);
        this.taskList.add(task);
        this.db.writeTasks(this.taskList);
        return task;
    }

    public Task addTask(Task task) {
        this.taskList.add(task);
        this.db.writeTasks(this.taskList);
        return task;
    }

    public Task deleteTask(int taskIdx) throws IllegalTaskNumberException {
        Task task = this.getTask(taskIdx);
        this.taskList.remove(taskIdx);
        this.db.writeTasks(this.taskList);
        return task;
    }

    public Task markTask(int taskIdx) throws IllegalTaskNumberException {
        Task task = this.getTask(taskIdx);
        task.markDone();
        this.db.writeTasks(this.taskList);
        return task;
    }

    public Task unmarkTask(int taskIdx) throws IllegalTaskNumberException {
        Task task = this.getTask(taskIdx);
        task.unmarkDone();
        this.db.writeTasks(this.taskList);
        return task;
    }

    private Task getTask(int taskIdx) throws IllegalTaskNumberException {
        if (taskIdx < 0 || taskIdx >= this.getTaskCount()) {
            throw new IllegalTaskNumberException(String.format("%d is not a valid task number", taskIdx));
        }
        return this.taskList.get(taskIdx);
    }
    public String getTaskListStr() {
        StringBuilder taskListStr = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            String taskInfo = String.format("%d.%s", i + 1, task.getTaskInfo());
            taskListStr.append(taskInfo).append("\n");
        }
        return taskListStr.toString().trim();
    }
}
