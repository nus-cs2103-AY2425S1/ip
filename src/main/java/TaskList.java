import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private List<Task> parent;
    private static int completed;
    private static int uncompleted;

    public TaskList(int size) {
        this.parent = new ArrayList<Task>(size);
    }

    public void addTask(String task) {
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                Choose a task type (1, 2 or 3):
                1. Todo - No end date
                2. Deadline - Has end date
                3. Event - Has start and end date
                """);
        String type = sc.nextLine();
        switch(type){
            case "1":
                this.parent.add(new Todo(task));
                uncompleted++;
                System.out.println("Friday > Okay, I've added a todo: " + task);
                break;
            case "2":
                System.out.println("What is the deadline?");
                this.parent.add(new Deadline(task, sc.nextLine()));
                uncompleted++;
                System.out.println("Friday > Okay, I've added a deadline: " + task);
                break;
            case "3":
                System.out.println("What is the start date?");
                String start = sc.nextLine();
                System.out.println("What is the end date?");
                this.parent.add(new Event(task, start, sc.nextLine()));
                uncompleted++;
                System.out.println("Friday > Okay, I've added an event: " + task);
                break;
            default:
                System.out.println("Invalid task type! Try adding again.");
        }
    }

    public void removeTask(int task) {
        Task temp = parent.get(task);
        this.parent.remove(task);
        if (temp.isDone()) {
            completed--;
        } else {
            uncompleted--;
        }
        System.out.println("Friday > Successfully removed: " + temp.toString());
    }

    @Override
    public String toString() {
        String ans = "";
        for (int i = 1; i <= parent.size(); i++) {
            ans += String.format("%d: %s%n", i, parent.get(i-1).toString());
        }
        return ans;
    }

    public void doneTask(String action, int task) {
        if (action.equals("mark") || action.equals("Mark")) {
            this.parent.get(task).setDone();
            completed++;
            uncompleted--;
            System.out.println("Friday > Good job! Marked as done :)");
        } else {
            this.parent.get(task).setUndone();
            uncompleted++;
            completed--;
            System.out.println("Friday > Oh man! Marked as undone :(");
        }
    }
}