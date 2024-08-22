import java.util.Scanner;
import java.util.ArrayList;

public class Pixel {
    public static String LINE = "\t" + "------------------------------------";
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static void printGreeting() {
        System.out.println(LINE);
        System.out.println("\t" + "Hello! I'm Pixel!");
        System.out.println("\t" + "What can I do for you?");
        System.out.println(LINE);
    }

    public static void printExit() {
        System.out.println(LINE);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void printList() {
        System.out.println(LINE);
        for (int index = 1; index <= tasks.size(); index++) {
            System.out.println("\t" + index + "." + tasks.get(index-1).getFullDescription());
        }
        System.out.println(LINE);
    }

    public static void processResponse() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.equals("list")) {
                printList();
            } else if (command.startsWith("mark")) {
                String[] stringArray = command.split(" ", 0);
                Task currentTask = tasks.get(Integer.parseInt(stringArray[1]) - 1);
                currentTask.markAsDone();
                System.out.println(LINE);
                System.out.println("\t" + "Nice! I've marked this task as done:");
                System.out.println("\t  " + currentTask.getFullDescription());
                System.out.println(LINE);
            } else if (command.startsWith("unmark")) {
                String[] stringArray = command.split(" ", 0);
                Task currentTask = tasks.get(Integer.parseInt(stringArray[1]) - 1);
                currentTask.markAsUndone();
                System.out.println(LINE);
                System.out.println("\t" + "OK, I've marked this task as not done yet:");
                System.out.println("\t  " + currentTask.getFullDescription());
                System.out.println(LINE);
            } else if (command.equals("bye")) {
                printExit();
                break;
            } else {
                Task newTask = new Task(command);
                tasks.add(newTask);
                System.out.println(LINE);
                System.out.println("\t" + "added: " + newTask.getDescription());
                System.out.println(LINE);
            }
        }
    }
    public static void main(String[] args) {
        printGreeting();
        processResponse();
    }
}
