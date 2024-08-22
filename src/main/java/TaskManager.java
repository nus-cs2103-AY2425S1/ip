public class TaskManager {
    private int taskCount;
    private Task[] tasks;
    private boolean taskStatus;
    public TaskManager() {
        taskCount = 0;
        tasks = new Task[100];
    }
    public void addTask(String description) {
        tasks[taskCount] = new Task(description);
        taskCount++;
        System.out.println("____________________________________________________________");
        System.out.println("added: " + description);
        System.out.println("____________________________________________________________");
    }

    public void listTask() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + ". " + tasks[i].toString());
        }
        System.out.println("____________________________________________________________");
    }

    public void markTask(int num) {
        tasks[num-1].complete();
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + tasks[num - 1].toString());
        System.out.println("____________________________________________________________");
    }

    public void unmarkTask(int num) {
        tasks[num-1].notComplete();
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + tasks[num - 1].toString());
        System.out.println("____________________________________________________________");
    }

    public String checkStatus() {
        if (taskStatus) {
            return "X";
        } else {
            return " ";
        }
    }
}
