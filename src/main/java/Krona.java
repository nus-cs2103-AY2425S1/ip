import java.util.Scanner;
import java.util.ArrayList;
public class Krona {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

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
                for (int i = 0; i < tasks.size(); i++ ) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else if (words[0].equals("mark")) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(taskIndex));
                } else {
                    System.out.println("There is no task with that number.");
                }
            } else if (words[0].equals("unmark")) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(taskIndex));
                } else {
                    System.out.println("There is no task with that number.");
                }
            } else if (words[0].equals("delete")) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                if ( taskIndex >= 0 && taskIndex < tasks.size()) {
                    Task removedTask = tasks.remove(taskIndex);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(removedTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    System.out.println("There is no task with that number.");
                }
            } else if (words[0].startsWith("todo")) {
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    System.out.println("The description of the todo is empty. Please add a description.");
                } else {
                    tasks.add(new ToDo(words[1]));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
            } else if (words[0].startsWith("deadline")) {
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    System.out.println("The description of the deadline is empty. Please add a description.");
                } else {
                    String[] parts = words[1].split("/by", 2);
                    if (parts.length < 2) {
                        System.out.println("The deadline must include a /by date.");
                    } else {
                        tasks.add(new Deadline(parts[0], parts[1]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                }
            } else if (words[0].startsWith("event")) {
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    System.out.println("OOPS!!! The description of an event cannot be empty.");
                } else {
                    String[] parts = words[1].split("/from ", 2);
                    if (parts.length < 2 || !parts[1].contains("/to ")) {
                        System.out.println("OOPS!!! The event must include a /from and /to time.");
                    } else {
                        String[] time = parts[1].split("/to ", 2);
                        tasks.add(new Event(parts[0], time[0], time[1]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                }
            } else {
                System.out.println("Unknown command. Please try again.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There is no task with that number.");
            }
        }
    }
}
