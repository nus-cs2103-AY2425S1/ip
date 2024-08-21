/**
 * This class implements a task list.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class TaskList {
    private Task[] taskList;
    private int numOfTasks;

    /**
     * Constructor for a TaskList. Initializes number of tasks to 0.
     */
    public TaskList() {
        this.taskList = new Task[100];
        this.numOfTasks = 0;
    }

    /**
     * Method to add task to the task list.
     *
     * @param task
     */
    public void addTask(Task task) {
        this.taskList[numOfTasks] = task;
        this.numOfTasks += 1;
        UI.response("Added \'" + task + "\' task!");
        String msg = String.format("By the way, you have %d tasks now!", this.numOfTasks);
        UI.response(msg);
    }

    /**
     * Method to display task list for user.
     */
    public void displayList() {
        UI.response("These are your tasks!");
        for (int i = 0; i < this.numOfTasks; i++) {
            Task currTask = this.taskList[i];
            String res;
            if (currTask.isComplete()) {
                res = String.format("%d.%s", i + 1, currTask);
            } else {
                res = String.format("%d.%s", i + 1, currTask);
            }
            UI.response(res);
        }
        UI.response("That's all your tasks for now :>>>");
    }

    /**
     * Signal that a task is complete and mark the description with an X.
     *
     * @param taskNum int to indicate which task to mark as complete.
     */
    public void markDoneTask(int taskNum) {
        if (taskNum <= 0 || taskNum > this.numOfTasks) {
            UI.response(String.format("I can't mark task %d cause it doesn't exist!!! ;-;", taskNum));
        } else {
            Task reqTask = this.taskList[taskNum - 1];
            reqTask.complete();
            UI.response("Oki, I'll mark the task as done *w*! Good job finishing the task!!");
            String res = String.format("%s", reqTask);
            UI.response(res);
        }
    }

}
