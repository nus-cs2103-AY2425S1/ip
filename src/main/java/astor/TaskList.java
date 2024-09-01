package astor;

import astor.exception.AstorException;
import astor.exception.DeleteTaskOutOfRangeException;
import astor.exception.MarkTaskOutOfRangeException;
import astor.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public String getTaskList() {
        int index = 1;
        StringBuilder s = new StringBuilder();
        for (Task task : taskList) {
            s.append("\n" + index + ". " + task.toString());
            index++;
        }
        return s.toString();
    }

    public String markTaskDone(int taskIndex, Storage storage) throws MarkTaskOutOfRangeException {
        if (taskIndex >= 1 && taskIndex <= taskList.size()) {
            Task task = taskList.get(taskIndex - 1);
            if (task.isDone()) {
                return "This astor.task is already done:\n" + taskIndex
                        + ". " + task;
            } else {
                task.markDone();
                storage.updateData(taskList);
                return "Nice! I've marked this astor.task as done:\n" + taskIndex
                        + ". " + task;
            }
        } else {
            throw new MarkTaskOutOfRangeException(this.size());
        }
    }

    public String unMarkTaskDone(int taskIndex, Storage storage) throws MarkTaskOutOfRangeException {
        if (taskIndex >= 1 && taskIndex <= taskList.size()) {
            Task task = taskList.get(taskIndex - 1);
            if (task.isDone()) {
                task.markUndone();
                storage.updateData(taskList);
                return "OK, I've marked this astor.task as not done yet:\n" + taskIndex
                        + ". " + task;
            } else {
                return "This astor.task is already marked as uncompleted:\n" + taskIndex
                        + ". " + task;
            }
        } else {
            throw new MarkTaskOutOfRangeException(this.size());
        }
    }

    public String deleteTask(int taskIndex, Storage storage) throws AstorException {
        if (taskList.isEmpty()) {
            throw DeleteTaskOutOfRangeException.noTaskToDelete();
        }
        if (taskIndex >= 1 && taskIndex <= taskList.size()) {
            Task task = taskList.remove(taskIndex - 1);
            storage.updateData(taskList);
            return "Noted. I've removed this astor.task:\n  " +
                    task + "\nNow you have " + taskList.size() + " tasks in the list.";
        } else {
            throw DeleteTaskOutOfRangeException.outOfRangeTaskToDelete(taskList.size());
        }
    }

    public String addTask(Task task, Storage storage) throws IOException {
        storage.appendToFile(task.dataDescription());
        taskList.add(task);
        return task.toString();
    }




    public int size() {
        return taskList.size();
    }

}
