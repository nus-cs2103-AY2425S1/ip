package chappy.util;

import chappy.exception.CreateTaskException;

import chappy.task.Task;

import java.io.IOException;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList =  new ArrayList<Task>();
    }

    /**
     * Adds supplied Task to task list, then saves changes to disk
     * using supplied Storage object.
     *
     * @param task Task object to add.
     * @param storage Storage object for saving to disk.
     * @throws IOException If Storage object has error saving to disk.
     */
    public void addTask(Task task, Storage storage) throws IOException {
        this.taskList.add(task);
        System.out.println("Alright sir! I've added this task:");
        System.out.println(task.toString());
        storage.saveToDisk(taskList);
    }

    /**
     * Removes Task at supplied deleteIndex
     * from task list, then saves changes to disk
     * using supplied Storage object.
     *
     * @param deleteIndex Index of Task to delete from task list.
     * @param storage Storage object for saving to disk.
     * @throws IOException If Storage object has error saving to disk.
     * @throws CreateTaskException If supplied index is out of bounds of task list.
     */
    public void removeTask(int deleteIndex, Storage storage) throws IOException, CreateTaskException {
        if (deleteIndex < 0 || deleteIndex > this.taskList.size() - 1) {
            throw new CreateTaskException("Oh SIR! That task index does not exist!");
        }
        Task task = this.taskList.get(deleteIndex);
        this.taskList.remove(task);
        System.out.println("Unfortunate.. I'll remove this task from the list..");
        System.out.println(task.toString());
        storage.saveToDisk(taskList);
    }

    /**
     * Marks Task at supplied markIndex in
     * task list as done, then saves changes to disk
     * using supplied Storage object.
     *
     * @param markIndex Index of Task in task list to mark as done.
     * @param storage Storage object for saving to disk.
     * @throws IOException If Storage object has error saving to disk.
     * @throws CreateTaskException If supplied index is out of bounds of task list.
     */
    public void markTaskAsDone(int markIndex, Storage storage) throws IOException, CreateTaskException {
        if (markIndex < 0 || markIndex > this.taskList.size() - 1) {
            throw new CreateTaskException("Oh SIR! That task index does not exist!");
        }
        taskList.get(markIndex).markAsDone();
        storage.saveToDisk(taskList);
    }

    /**
     * Marks Task at supplied unmarkIndex in
     * task list as not done, then saves changes to disk
     * using supplied Storage object.
     *
     * @param unmarkIndex Index of Task in task list to mark as not done.
     * @param storage Storage object for saving to disk.
     * @throws IOException If Storage object has error saving to disk.
     * @throws CreateTaskException If supplied index is out of bounds of task list.
     */
    public void markTaskAsNotDone(int unmarkIndex, Storage storage) throws IOException, CreateTaskException {
        if (unmarkIndex < 0 || unmarkIndex > this.taskList.size() - 1) {
            throw new CreateTaskException("Oh SIR! That task index does not exist!");
        }
        taskList.get(unmarkIndex).markAsNotDone();
        storage.saveToDisk(taskList);
    }

    public void listTasks() {
        System.out.println("As requested, here are your outstanding tasks sir:");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println(i + 1 + "." + this.taskList.get(i).toString());
        }
    }

}
