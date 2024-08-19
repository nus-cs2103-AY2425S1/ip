public class TaskList {
    private static Task[] list = new Task[100];
    private static int idx = 0;
    public static void addTask(Task task) {
        list[idx] = task;
        idx++;
    }

    public static void listTask() {
        for(int i=1; i<=idx; i++) {
            Task currTask = list[i-1];
            System.out.println(String.format("%d. %s", i, currTask.getDes()));
        }
    }
}
