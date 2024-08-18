package calebyyy;

import calebyyy.Tasks.Task;

public class Calebyyy {
    private Task[] tasks;
    private int taskCount;
    private CommandManager commandManager;

    public Calebyyy() {
        tasks = new Task[100];
        taskCount = 0;
        commandManager = new CommandManager(this);
    }

    public void start() {
        greet();
        commandManager.startCommandLoop();
    }
    

    public void listTasks() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    public void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
    }

    public void stop() {
        System.exit(0);
    }

    public void markTask(int taskNumber) {
        tasks[taskNumber - 1].markAsDone();
    }

    public void unmarkTask(int taskNumber) {
        tasks[taskNumber - 1].markAsNotDone();
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Calebyyy");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        new Calebyyy().start();
    }
}