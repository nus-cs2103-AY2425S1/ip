public class TaskTracker {
    private String[] taskList;
    private int counter;

    public TaskTracker() {
        this.taskList = new String[100];
        this.counter = 0;
    }
    public void getList() {
        if (this.counter == 0) {
            System.out.println("No tasks currently!");
        } else {
            for (int i = 0; i < this.counter; i++) {
                System.out.println("" + (i + 1) + ". " + this.taskList[i]);
            }
        }
    }

    public void updateList(String s) {
        taskList[this.counter] = s;
        this.counter++;
    }
}