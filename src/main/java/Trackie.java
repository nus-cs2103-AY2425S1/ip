import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class Trackie {
    private ArrayList<Task> tasks;

    public Trackie() {
        tasks = new ArrayList<>();
    }

    public void addTask(String description) {
        tasks.add(new Task(description));
        System.out.println(String.format("Added: %s", description));
    }

    public void listTasks() {
        int counter = 1;
        for (Task t : tasks) {
            System.out.println(String.format("%d. [%s] %s", counter, t.getStatusIcon(), t.getTaskInfo()));
            counter++;
        }
    }

    public void markTask(int index) {
        Task t = tasks.get(index - 1);
        t.markDone();
        System.out.println("Amazing! The specified task is now marked as complete:");
        System.out.println(String.format("[%s] %s", t.getStatusIcon(), t.getTaskInfo()));
    }

    public void unmarkTask(int index) {
        Task t = tasks.get(index - 1);
        t.markUndone();
        System.out.println("Alright, the specified task has been marked undone.");
        System.out.println(String.format("[%s] %s", t.getStatusIcon(), t.getTaskInfo()));
    }

    public void greet() {
        System.out.println("Hello, I'm Trackie. Nice to meet you =)");
        System.out.println("Type something to add it as a task.");
        System.out.println("Type \"list\" to see the list of tasks");
        System.out.println("Type \"mark\" or \"unmark\" followed by the task number to mark said task as done or undone.");
        System.out.println("Type \"bye\" to exit.");
    }

    public void exit() {
        System.out.println("Seeya!");
    }

    public static void main(String[] args) {

        Trackie bot = new Trackie();
        bot.greet();
        while (true) {
            Scanner s = new Scanner(System.in);
            System.out.print("Type something: ");
            String userInput = s.nextLine();
            String[] terms = userInput.split(" ");

            if (userInput.equals("bye")) {
                bot.exit();
                break;
            }

            if (terms[0].equals("list")) {
                bot.listTasks();
                continue;
            } else if (terms[0].equals("mark")) {
                int index = Integer.parseInt(terms[1]);
                bot.markTask(index);
                continue;
            } else if (terms[0].equals("unmark")) {
                int index = Integer.parseInt(terms[1]);
                bot.unmarkTask(index);
                continue;
            }
            bot.addTask(userInput);
        }

    }
}
