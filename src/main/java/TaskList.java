/**
 * This class implements a task list.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class TaskList {
    private String[] taskList;
    private int numOfTasks;

    /**
     * Constructor for a TaskList. Initializes number of tasks to 0.
     */
    public TaskList() {
        this.taskList = new String[100];
        this.numOfTasks = 0;
    }

    /**
     * Method to add task to the task list.
     *
     * @param task
     */
    public void addTask(String task) {
        this.taskList[numOfTasks] = task;
        this.numOfTasks += 1;
        UI.response("added \'" + task + "\' task!");
    }

    /**
     * Method to display task list for user.
     */
    public void displayList() {
        for (int i = 0; i < this.numOfTasks; i++) {
            String res = String.format("%d. %s", i + 1, this.taskList[i]);
            UI.response(res);
        }
        UI.response("That's all your tasks for now :>>>");
    }

}
