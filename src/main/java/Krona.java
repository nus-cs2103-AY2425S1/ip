import java.util.Scanner;
public class Krona {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int allTasks = 0;

        System.out.println("Hello! I'm Krona\n" +
                "What can i Do for you?");

        while (true) {
            String input = sc.nextLine();
            String[] words = input.split(" ", 2);

            try {
            if (words[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (words[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < allTasks; i++ ) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else if (words[0].equals("mark")) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                if (taskIndex >= 0 && taskIndex < allTasks && tasks[taskIndex] != null) {
                    tasks[taskIndex].markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[taskIndex]);
                } else {
                    System.out.println("There is no task with that number.");
                }
            } else if (words[0].equals("unmark")) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                if (taskIndex >= 0 && taskIndex < allTasks && tasks[taskIndex] != null) {
                    tasks[taskIndex].markNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks[taskIndex]);
                } else {
                    System.out.println("There is no task with that number.");
                }
            } else if (words[0].startsWith("todo")) {
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    System.out.println("The description of the todo is empty. Please add a description.");
                } else {
                    tasks[allTasks] = new ToDo(words[1]);
                    allTasks++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[allTasks - 1]);
                    System.out.println("Now you have " + allTasks + " tasks in the list.");
                }
            } else if (words[0].startsWith("deadline")) {
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    System.out.println("The description of the deadline is empty. Please add a description.");
                } else {
                    String[] parts = words[1].split("/by ", 2);
                    tasks[allTasks] = new Deadline(parts[0], parts[1]);
                    allTasks++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[allTasks - 1]);
                    System.out.println("Now you have " + allTasks + " tasks in the list.");
                }
            } else if (words[0].startsWith("event")) {
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    System.out.println("The description of the event is empty. Please add a description.");
                } else {
                    String[] parts = words[1].split("/from ", 2);
                    String[] time = parts[1].split("/to ", 2);
                    tasks[allTasks] = new Event(parts[0], time[0], time[1]);
                    allTasks++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[allTasks - 1]);
                    System.out.println("Now you have " + allTasks + " tasks in the list.");
                }
            }
             else {
                System.out.println("Unknown command. Please try again.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There is no task with that number.");
            }
        }
    }
}
