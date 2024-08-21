import java.util.ArrayList;
import java.util.Scanner;

public class Dude {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void addTask(String description) {
        taskList.add(new Task(description));
        System.out.println("added: " + description);
    }

    public static void markTaskAsDone(int index) {
        taskList.get(index - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(index - 1));
    }

    public static void markTaskAsUndone(int index) {
        taskList.get(index - 1).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.get(index - 1));
    }

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i));
        }
    }

    public static void action(String input) {
        if (input.startsWith("mark ")) {
            markTaskAsDone(Integer.parseInt(input.substring(5)));
        } else if (input.startsWith("unmark ")) {
            markTaskAsUndone(Integer.parseInt(input.substring(7)));
        } else if (input.equals("list")) {
            printList();
        } else {
            addTask(input);
        }
    }
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Dude!\nWhat can I do for you?");
        System.out.println(line);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(line);
            action(input);
            System.out.println(line);
            input = sc.nextLine();
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again!");
        System.out.println(line);
    }
}
