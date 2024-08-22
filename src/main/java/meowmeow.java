import java.util.LinkedList;
import java.util.Scanner;
public class meowmeow {
    public static void main(String[] args) {
       /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("Hello! I'm meowmeow\n" + "What can I do for you?\n");
        LinkedList<Task> list = new LinkedList<>();
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                // Print all tasks
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." + list.get(i));
                }
            } else if (input.startsWith("mark ")) {
                // Mark a task as done
                int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                if (taskNumber >= 0 && taskNumber < list.size()) {
                    list.get(taskNumber).markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + list.get(taskNumber));
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (input.startsWith("unmark ")) {
                // Unmark a task (mark it as not done)
                int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                if (taskNumber >= 0 && taskNumber < list.size()) {
                    list.get(taskNumber).unMark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + list.get(taskNumber));
                } else {
                    System.out.println("Invalid task number.");
                }
            } else {
                // Add a new task to the list
                Task t = new Task(input);
                list.add(t);
                System.out.println("added: " + input);
            }
            input = s.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}