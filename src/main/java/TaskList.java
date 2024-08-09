public class TaskList {
    private static Task[] tasks;
    private static int taskCount;

    public TaskList() {
        tasks = new Task[100];
        taskCount = 0;
    }

    public void addTask(String description) {
        if (taskCount <= 100) {
            tasks[taskCount++] = new Task(description);
            System.out.println("added: " + description + "\n");
        } else {
            System.out.println("Task list is full!\n");
        }
    }

    public void listTasks() {
        if (taskCount == 0) {
            System.out.println("No tasks.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i ++) {
            System.out.println(tasks[i]);
        }
    }

    public void markTaskAsDone(int index) {
        if (index > 0 && index <= taskCount) {
            tasks[index - 1].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[index - 1]);
        } else {
            System.out.println("Invalid Task Number");
        }
    }

    public void markTaskAsNotDone(int index) {
        if (index > 0 && index <= taskCount) {
            tasks[index - 1].markAsNotDone();
            System.out.println("Ok! I've marked this task as not done yet:");
            System.out.println(tasks[index - 1]);
        } else {
            System.out.println("Invalid Task Number");
        }
    }
}
