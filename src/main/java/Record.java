public class Record {
    private Task [] tasks;
    private int count;

    public Record() {
        tasks = new Task[100];
        count = 0;
    }

    public void addTask(Task task) {
        if (count < tasks.length) {
            tasks[count] = task;
            count++;
        } else {
            System.out.println("Task array is full.");
        }
    }

}
