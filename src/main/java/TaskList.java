public class TaskList {
    private final Task[] list;
    private int numOfTasks;

    //max of task list
    public TaskList(int i) {
        list = new Task[i];
    }

    public boolean addTask(String task) {
        if (checkListSize()) {
            list[numOfTasks] = new Task(task);
            numOfTasks++;
            return true;
        } return false;
    }

    private boolean checkListSize() {
        return !(numOfTasks > 100);
    }

    public int getNumOfTasks() {
        return numOfTasks;
    }
    public Task getTask(int i) {
        return list[i];
    }



}
