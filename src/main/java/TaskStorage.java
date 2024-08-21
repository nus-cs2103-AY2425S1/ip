public class TaskStorage {
    private int maxStorage;
    private Task[] taskArray;
    private int numberOfTasks;

    public TaskStorage(int maxStorage) {
        this.maxStorage = maxStorage;
        this.taskArray = new Task[this.maxStorage];
        this.numberOfTasks = 0;
    }

    public void addTask(Task task) {
        this.taskArray[this.numberOfTasks] = task;
        System.out.println("\tAdded: " + task);
        this.numberOfTasks++;
    }

    public void markTask(int i) {
        Task task = this.taskArray[i - 1];
        task.markDone();
        System.out.println("\tGood Job! The task is now marked as done: ");
        System.out.println("\tMarked task: " + task);
    }

    public void unmarkTask(int i) {
        Task task = this.taskArray[i - 1];
        task.markNotDone();
        System.out.println("\tAlright, the task is marked as not done: ");
        System.out.println("\tUnmarked task: " + task);
    }

    public void listAllTasks() {
        for (int i = 0; i < numberOfTasks; i++) {
            int num = i + 1;
            Task task = taskArray[i];
            System.out.println("\t" + num + ". " + task);
        }
    }
}
