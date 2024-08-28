package fridayproject;

import java.util.Scanner;

public class Friday {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Friday\nWhat can I do for you?\n";
        String farewell = "Bye. Hope to see you again soon!";
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in); 
        String line = "______________________________________";
        System.out.println(greeting + line);
        Tasks[] tasks = new Tasks[100];

        while (true) {
            String inputString = scanner.nextLine().trim();
            System.out.println(line);
            if (inputString.equals("bye")) {
                System.out.println(farewell);
                System.out.println(line);
                break;
            } else if (inputString.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] != null) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }
                System.out.println(line);
            } else if (inputString.startsWith("mark ")) {
                int taskNum = Integer.parseInt(inputString.substring(5)) - 1;
                if (taskNum >= 0 && taskNum < tasks.length && tasks[taskNum] != null) {
                    tasks[taskNum].markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n  [X] " + tasks[taskNum].description);
                }
            } else if (inputString.startsWith("unmark ")) {
                int taskNum = Integer.parseInt(inputString.substring(7)) - 1;
                if (taskNum >= 0 && taskNum < tasks.length && tasks[taskNum] != null) {
                    tasks[taskNum].markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:\n  [ ] " + tasks[taskNum].description);
                }
            } else {
                System.out.println("added: " + inputString);
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] == null) {
                        tasks[i] = new Tasks(inputString);
                        break;
                    }
                }
                System.out.println(line);
            }
        }
    }
}