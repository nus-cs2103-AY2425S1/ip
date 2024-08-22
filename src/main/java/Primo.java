import java.util.ArrayList;
import java.util.Scanner;

public class Primo {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean ended = false;
    private static ArrayList<Task> list = new ArrayList<>();

    private static void printList() {
        int len = list.size();
        for (int i = 0; i < len; i++) {
            String output = String.valueOf(i + 1) + "." + list.get(i);
            System.out.println(output);
        }
    }

    private static void sayBye() {
        String byeMessage = "\nEl Primo:\n" +
                            "Bye. Hope to see you again soon!";
        System.out.println(byeMessage);
        ended = true;
    }

    public static void assessInput(String input) {
        String[] words = input.split(" ");
        if (input.equals("bye")) {
            sayBye();
        } else if (input.equals("list")) {
            System.out.println("\nEl Primo:");
            System.out.println("Here are the tasks in your list:");
            printList();
        } else if (words[0].equals("mark")) {
            int index = Integer.valueOf(words[1]) - 1;
            list.get(index).markAsDone();
            System.out.println("\nEl Primo:");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list.get(index));
        } else if (words[0].equals("unmark")) {
            int index = Integer.valueOf(words[1]) - 1;
            list.get(index).markAsUndone();
            System.out.println("\nEl Primo:");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(list.get(index));
        } else if (words[0].equals("todo")) {
            int fromIndex = input.indexOf("todo ") + 5;
            String description = input.substring(fromIndex);
            Task newTask = new ToDoTask(description);
            list.add(newTask);
            System.out.println("\nEl Primo:");
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.printf("Now you have %d tasks in the list.%n", list.size());
        } else if (words[0].equals("deadline")) {
            int fromIndex = input.indexOf("deadline ") + 9;
            int toIndex = input.indexOf("/by ");
            String description = input.substring(fromIndex, toIndex - 1);
            String dueTime = input.substring(toIndex + 4);
            Task newTask = new DeadlineTask(description, dueTime);
            list.add(newTask);
            System.out.println("\nEl Primo:");
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.printf("Now you have %d tasks in the list.%n", list.size());
        } else if (words[0].equals("event")) {
            int fromIndex = input.indexOf("event ") + 6;
            int toIndex = input.indexOf("/from ");
            int finalIndex = input.indexOf("/to ");
            String description = input.substring(fromIndex, toIndex - 1);
            String from = input.substring(toIndex + 6, finalIndex - 1);
            String to = input.substring(finalIndex + 4);
            Task newTask = new EventTask(description, from, to);
            list.add(newTask);
            System.out.println("\nEl Primo:");
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.printf("Now you have %d tasks in the list.%n", list.size());
        } else {
            Task newTask = new Task(input);
            list.add(newTask);
            String output = "\nEl Primo: \n" + "Added: " + input;
            System.out.println(output);
        }
    }
    public static void readInput() {
        System.out.println("\nMe:");
        String input = scanner.nextLine();
        assessInput(input);
    }

    public static void main(String[] args) {
        System.out.println("""
                El Primo:
                Hello! I'm El Primo!!
                What can I do for you?""");

        while (!ended) {
            readInput();
        }
    }
}
