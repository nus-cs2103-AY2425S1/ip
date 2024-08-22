import java.util.ArrayList;

public class TaskList {
    private Task[] tasklist;
    private int numberOfTask;

    public TaskList() {
        this.tasklist = new Task[100];
        this.numberOfTask = 0;
    }

    public void addTask(String name) {
        Task newTask = new Task(name);
        this.tasklist[numberOfTask] = newTask;
        numberOfTask++;
        System.out.println("added: " + name + "\n");
    }

    public void listTask() {
        for (int i = 0; i < numberOfTask; i++) {
            System.out.println((i + 1) + ". " + tasklist[i]);
        }
    }

    public void markTask(int index) {
        this.tasklist[index - 1].complete();
        System.out.println("Marked as done: \n" + this.tasklist[index - 1]);
    }

    public void unmarkTask(int index) {
        this.tasklist[index - 1].uncomplete();
        System.out.println("Marked as undone: \n" + this.tasklist[index - 1]);
    }
}