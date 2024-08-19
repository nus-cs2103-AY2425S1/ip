public class TaskList {
    private static final Task[] list = new Task[100];
    private static int idx = 0;
    public static void addTask(Task task) {
        list[idx] = task;
        idx++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.getDes());
        System.out.printf("Now you have %d tasks in the list.%n", idx);
    }

    public static void markTask(int x) {
        Task currTask = list[x-1];
        currTask.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + currTask.getDes());
    }

    public static void unmarkTask(int x) {
        Task currTask = list[x-1];
        currTask.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + currTask.getDes());
    }

    public static void listTask() {
        System.out.println("Here are the tasks in your list:");
        for(int i=1; i<=idx; i++) {
            Task currTask = list[i-1];
            System.out.printf("%d.%s%n", i, currTask.getDes());
        }
    }
}
