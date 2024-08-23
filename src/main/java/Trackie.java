import java.util.Scanner;
import java.util.ArrayList;

public class Trackie {
    private ArrayList<String> tasks;

    public Trackie() {
        tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        tasks.add(task);
        System.out.println(String.format("Added: %s", task));
    }

    public void listTasks() {
        int counter = 1;
        for (String s : tasks) {
            System.out.println(String.format("%d. %s", counter, s));
            counter++;
        }
    }

    public void greet() {
        System.out.println("Hello, I'm Trackie. Nice to meet you =)");
        System.out.println("Type something to add it as a task.");
        System.out.println("Note: type \"bye\" to exit.");
        System.out.println("Note: type \"list\" to see the list of tasks");
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
            if (userInput.equals("bye")) {
                bot.exit();
                break;
            } else if (userInput.equals("list")) {
                bot.listTasks();
            } else {
                bot.addTask(userInput);
            }
        }



    }
}
