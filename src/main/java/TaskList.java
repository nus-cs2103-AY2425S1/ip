import java.util.ArrayList;

public class TaskList {
    // replaces the ArrayList<Task> listOfTasks inside Janet
    private final ArrayList<Task> listOfTasks;

    TaskList() {
        listOfTasks = new ArrayList<>();
    }


    TaskList(ArrayList<Task> tasks) {
        // overloaded constructor to load the tasks created from janet.txt
        this.listOfTasks = tasks;
    }


    public int getNumberOfTasks() {
        return this.listOfTasks.size();
    }


    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }


    public Task getTask(int index) {
        return this.listOfTasks.get(index);
    }


    /**
     * @param task a Task object that is to be added into the listOfTasks
     */
    public void addTaskToList(Task task) {
        this.listOfTasks.add(task);
    }


    /**
     * Level 3 - mark as done
     * marks the desired task as done, by setting the boolean value of Task.done = true.
     * @param desiredTaskNum specifies the index of the task, inside listOfTasks, + 1.
     * @return a String message to indicate successful marking of desired task as done (done = true).
     */
    public String markAsDone(int desiredTaskNum) {
        // (desiredTaskNum - 1) is the index of the task, inside listOfTasks, that needs to be marked as done
        Task desiredTask = listOfTasks.get(desiredTaskNum - 1);
        if (desiredTask.isDone()) {
            // the desired task is already marked as done
            return "already marked";
        }
        desiredTask.setDone(true);
        return "marked";
    }


    /**
     * Level 3 - mark as done (unmark)
     * marks the desired task as NOT done, by setting the boolean value of Task.done = false.
     * @param desiredTaskNum specifies the index of the task, inside listOfTasks, + 1.
     * @return a String message to indicate successful unmarking of desired task (done = false).
     */
    public String unmark(int desiredTaskNum) {
        // (desiredTaskNum - 1) is the index of the task, inside listOfTasks, that needs to be unmarked
        Task desiredTask = listOfTasks.get(desiredTaskNum - 1);
        if (!desiredTask.isDone()) {
            // desired task is already marked as NOT done (unmarked)
            return "already unmarked";
        }
        desiredTask.setDone(false);
        return "unmarked";
    }


    /**
     * Level 6 - delete task
     * @param desiredTaskNum specifies the index of the task, inside listOfTasks, + 1.
     * @return a String message to indicate successful deletion of the desired task from listOfTasks.
     */
    public String deleteTask(int desiredTaskNum) {
        Task desiredTask = listOfTasks.get(desiredTaskNum - 1);
        listOfTasks.remove(desiredTask);
        return "task deleted";
    }

}
