public class TaskTracker {
    private Task[] taskList;
    private int counter;

    public TaskTracker() {
        this.taskList = new Task[100];
        this.counter = 0;
    }
    public void getList() {
        if (this.counter == 0) {
            System.out.println("No tasks currently!");
        } else {
            System.out.println("Here are your tasks!!! Remember to complete them!!!");
            for (int i = 0; i < this.counter; i++) {
                if (this.taskList[i].getIsDone() == false) {
                    System.out.println("" + (i + 1) + ". [ ] " + this.taskList[i].getName());
                } else {
                    System.out.println("" + (i + 1) + ". [X] " + this.taskList[i].getIsDone());
                }

            }
        }
    }

    public void updateList(String s) {
        taskList[this.counter] = new Task(s);
        this.counter++;
    }
}