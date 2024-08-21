import java.util.ArrayList;
import java.util.Scanner;

public class Dude {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void addTask(String input) {
        if (input.startsWith("todo ")) {
            taskList.add(new Todo(input.substring(5)));
        } else if (input.startsWith("deadline ")) {
            String[] arr = input.substring(9).split(" /by ");
            taskList.add(new Deadline(arr[0], arr[1]));
        } else if (input.startsWith("event ")) {
            String[] arr = input.substring(6).split(" /");
            taskList.add(new Event(arr[0], arr[1].substring(5), arr[2].substring(3)));
        }
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void markTaskAsDone(int index) {
        if (index > taskList.size() || index < 1) {
            System.out.println("This task does not exist!");
        } else if (taskList.get(index - 1).isDone) {
            System.out.println("This task is already marked as done!");
        } else {
            taskList.get(index - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskList.get(index - 1));
        }
    }

    public static void markTaskAsUndone(int index) {
        if (index > taskList.size() || index < 1) {
            System.out.println("This task does not exist!");
        } else if (!taskList.get(index - 1).isDone) {
            System.out.println("This task is already marked as not done!");
        } else {
            taskList.get(index - 1).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(taskList.get(index - 1));
        }
    }

    public static void printList() {
        if (taskList.isEmpty()) {
            System.out.println("There are no tasks in your list!");
            return;
        }
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
        } else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
            addTask(input);
        } else {
            System.out.println("I'm sorry, but I don't know what that means :(");
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
