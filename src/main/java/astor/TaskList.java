package astor;

import astor.exception.AstorException;
import astor.exception.DeleteTaskOutOfRangeException;
import astor.exception.MarkTaskOutOfRangeException;
import astor.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks and provides functionalities to modify and access tasks.
 *
 * The TaskList class allows for operations such as adding, marking, unmarking, and deleting tasks.
 * It also provides methods to retrieve the list of tasks and their status.
 */
public class TaskList {
    List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the string representation of all tasks.
     *
     * @return string information of all tasks
     */
    public String getTaskList() {
        int index = 1;
        StringBuilder s = new StringBuilder();
        for (Task task : taskList) {
            s.append("\n" + index + ". " + task.toString());
            index++;
        }
        return s.toString();
    }

    /**
     * Marks a task as completed.
     *
     * @param taskIndex the index of the task starting from 1 if the list is not empty
     * @param storage the data file
     * @return reply of the chatbot after marking tasks as done
     * @throws MarkTaskOutOfRangeException if an invalid taskIndex is given by user
     */
    public String markTaskDone(int taskIndex, Storage storage) throws MarkTaskOutOfRangeException {
        if (taskIndex >= 1 && taskIndex <= taskList.size()) {
            Task task = taskList.get(taskIndex - 1);
            if (task.isDone()) {
                return "This task is already done:\n" + taskIndex
                        + ". " + task;
            } else {
                task.markDone();
                storage.updateData(taskList);
                return "Nice! I've marked this task as done:\n" + taskIndex
                        + ". " + task;
            }
        } else {
            throw new MarkTaskOutOfRangeException(this.size());
        }
    }

    /**
     * Marks a task as uncompleted.
     *
     * @param taskIndex the index of the task starting from 1 if the list is not empty
     * @param storage the data file
     * @return reply of the chatbot after marking tasks as done
     * @throws MarkTaskOutOfRangeException if an invalid taskIndex is given by user
     */
    public String unMarkTaskDone(int taskIndex, Storage storage) throws MarkTaskOutOfRangeException {
        if (taskIndex >= 1 && taskIndex <= taskList.size()) {
            Task task = taskList.get(taskIndex - 1);
            if (task.isDone()) {
                task.markUndone();
                storage.updateData(taskList);
                return "OK, I've marked this task as not done yet:\n" + taskIndex
                        + ". " + task;
            } else {
                return "This task is already marked as uncompleted:\n" + taskIndex
                        + ". " + task;
            }
        } else {
            throw new MarkTaskOutOfRangeException(this.size());
        }
    }

    /**
     * Deletes tasks from taskList.
     *
     * @param taskIndex the index of the task starting from 1 if the list is not empty
     * @param storage the data file
     * @return reply of the chatbot after deleting a task
     * @throws AstorException if there are no tasks to delete or taskIndex provided is out of range
     */
    public String deleteTask(int taskIndex, Storage storage) throws AstorException {
        if (taskList.isEmpty()) {
            throw DeleteTaskOutOfRangeException.noTaskToDelete();
        }
        if (taskIndex >= 1 && taskIndex <= taskList.size()) {
            Task task = taskList.remove(taskIndex - 1);
            storage.updateData(taskList);
            return "Noted. I've removed this task:\n  " +
                    task + "\nNow you have " + taskList.size() + " tasks in the list.";
        } else {
            throw DeleteTaskOutOfRangeException.outOfRangeTaskToDelete(taskList.size());
        }
    }

    /**
     * Adds a new task to the list.
     *
     * This method adds the specified task to the task list and appends its data description to the storage file.
     *
     * @param task the task to add to the list
     * @param storage the storage object used to append data to the file
     * @return a string representation of the added task
     * @throws IOException if an I/O error occurs while appending to the file
     */
    public String addTask(Task task, Storage storage) throws IOException {
        storage.appendToFile(task.dataDescription());
        taskList.add(task);
        return task.toString();
    }

    public int size() {
        return taskList.size();
    }

}
