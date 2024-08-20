import jdk.jfr.Event;

import java.util.Scanner;

public class Elara {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
        int count = 0;

        System.out.println("Hello! I'm Elara");
        System.out.println("What can I do for you?");

        while (true) {
            String text = scanner.nextLine().strip();
            if (text.equalsIgnoreCase("bye")) {
                break;
            } else if (text.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    if (list[i] == null) {
                        break;
                    }
                    int j = i + 1;
                    System.out.println(j + ". " + list[i]);
                }
            } else if (text.toLowerCase().startsWith("mark")) {
                int i = Integer.parseInt(text.substring(5)) - 1;
                if (i >= 0 && i < count) {
                    list[i].mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list[i]);
                }
            } else if (text.toLowerCase().startsWith("unmark")) {
                int i = Integer.parseInt(text.substring(7)) - 1;
                if (i >= 0 && i < count) {
                    list[i].unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list[i]);
                }
            } else if (text.toLowerCase().startsWith("todo")) {
                ToDoTask newTask = new ToDoTask(text.substring(5));
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask);
                System.out.printf("Now you have %d tasks in the list%n", count + 1);
                list[count] = newTask;
                count++;
            } else if (text.toLowerCase().startsWith("deadline")) {
                int i = text.lastIndexOf("/by ");
                String description = text.substring(9, i - 1);
                String deadline = text.substring(i + 4);
                DeadlineTask newTask = new DeadlineTask(description, deadline);
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask);
                System.out.printf("Now you have %d tasks in the list%n", count + 1);
                list[count] = newTask;
                count++;
            } else if (text.toLowerCase().startsWith("event")) {
                int i = text.lastIndexOf("/from ");
                int j = text.lastIndexOf("/to ");
                String description = text.substring(5, i - 1);
                String start = text.substring(i + 6, j - 1);
                String end = text.substring(j + 3);
                EventTask newTask = new EventTask(description, start, end);
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask);
                System.out.printf("Now you have %d tasks in the list%n", count + 1);
                list[count] = newTask;
                count++;
            }
        }

        System.out.println("Bye. Hope to see you again!");
    }
}
