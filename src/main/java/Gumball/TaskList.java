package Gumball;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;
    public int n = 0;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Returns String which provides information on Task being added.
     * @param adding The Task being added to the TaskList.
     * @return information of the Task being added.
     * @throws InputErrorException
     */
    public String add(Task adding) throws InputErrorException {
        tasks.add(adding);
        n++;
        return getTaskString(n);
    }

    /**
     * Returns number of tasks in list.
     * @return number of tasks in list.
     */
    public int getNumOfTasks() {
        return n;
    }

    /**
     * Marks the task in the list stored in the ith index.
     * @param i Index of task.
     * @throws InputErrorException
     */

    public void mark(int i) throws InputErrorException {
        if (tasks.isEmpty()) {
            throw new InputErrorException("Sorry your task list is currently empty");
        }
        try {
            tasks.get(i - 1).markDone();
        } catch (IndexOutOfBoundsException e) {
            throw new InputErrorException(
                    "Sorry you do not have that many Tasks in your list");
        } catch (NullPointerException e) {
            throw new InputErrorException("Sorry your task list is currently empty");
        }
    }

    /**
     * Returns a String which provides information on Task at the ith index.
     * @param i Index of task.
     * @return String which provides information on Task.
     * @throws InputErrorException
     */
    public String getTaskString(int i) throws InputErrorException {
        if (tasks.isEmpty()) {
            throw new InputErrorException("Sorry your task list is currently empty");
        }
        try {
            String temp = "";
            Task task = tasks.get(i - 1);
            temp = temp + task.getTaskType();
            temp = temp + task.getStatusIcon() + " ";
            temp = temp + task.getDescription();
            return temp;
        } catch (IndexOutOfBoundsException e) {
            if (i <= 0) {
                throw new InputErrorException(
                        "Invalid input task number cannot be zero or less");
            } else {
                throw new InputErrorException(
                        "Sorry you do not have that many Tasks in your list");
            }
        } catch (NullPointerException e) {
            throw new InputErrorException("Sorry your task list is currently empty.");
        }
    }

    /**
     * Returns task stored at the ith index in the list.
     * @param i Index of task.
     * @return task stored at the ith index in the list.
     * @throws InputErrorException
     */
    public Task getTask(int i) throws InputErrorException {
        try {
            return tasks.get(i - 1);
        } catch (IndexOutOfBoundsException e) {
            if (i <= 0) {
                throw new InputErrorException(
                        "Invalid input task number cannot be zero or less");
            } else {
                throw new InputErrorException(
                        "Sorry you do not have that many Tasks in your list");
            }
        }
    }

    /**
     * Returns String which is a list of the information of all the tasks in the list.
     * @return List of information on all tasks.
     */
    public String getListAsString() {
        String temp = "";
        for (int i = 0; i < n; i++) {
            Task task = tasks.get(i);
            temp = String.format("%s %d.", temp, i + 1);
            temp = temp + task.getTaskType();
            temp = temp + task.getStatusIcon() + " ";
            temp = temp + task.getDescription();
            if (i < n - 1) {
                temp = temp + "\n";
            }
        }
        return temp;
    }


    /**
     * Deletes the task at the ith index and provides information on task deleted.
     * Returns a String which provides information on Task at the ith index.
     * @param i Index of task.
     * @return String which provides information on Task at the ith index.
     * @throws InputErrorException
     */
    public String delete(int i) throws InputErrorException {
        String str = getTaskString(i);
        tasks.remove(i - 1);
        n--;
        return str;
    }

    public String find(String keyword) throws InputErrorException {
        TaskList tempList = new TaskList();
        for (int i = 0; i < n; i++) {
            Task tempTask = tasks.get(i);
            if (tempTask.description.contains(keyword)) {
                tempList.add(tempTask);
            }
        }
        return tempList.getListAsString();
    }
}
