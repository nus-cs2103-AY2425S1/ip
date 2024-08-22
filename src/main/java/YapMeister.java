import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class YapMeister {
    final static int MAX_TASKS = 100;
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                               Hello I'm YapMeister
                               YAPYAPYAPYAP
                               """);
        String input = "";
        boolean running = true;
        Task[] tasks = new Task[MAX_TASKS];
        int taskIndex = 0;
        while (running) {
            System.out.print("\n");
            input = scanner.nextLine();
            String[] command = input.split(" ");
            switch (command[0]) {
                case "bye":
                    running = false;
                    break;
                case "list":
                    for (int i = 0; i < taskIndex; i++) {
                        System.out.println(String.format("%d. %s", i + 1, tasks[i].toString()));
                    }
                    break;
                case "mark":
                    //might need error handling
                    int index = parseInt(command[1]) - 1;
                    tasks[index].setCompleted(true);
                    System.out.println("You did this:");
                    System.out.println(tasks[index].toString());
                    break;
                case "unmark":
                    int umindex = parseInt(command[1]) - 1;
                    tasks[umindex].setCompleted(false);
                    System.out.println("You undid this:");
                    System.out.println(tasks[umindex].toString());
                    break;
                case "todo":
                    tasks[taskIndex] = new ToDo(input.split("todo ")[1]);
                    System.out.println("Added:");
                    tasks[taskIndex].toString();
                    taskIndex++;
                    System.out.println(String.format("You have %d tasks", taskIndex));
                    break;
                case "deadline":
                    String name = input.split("deadline ")[1].split(" /by")[0];
                    String deadline = input.split("/by ")[1];
                    tasks[taskIndex] = new Deadline(name, deadline);
                    System.out.println("Added:");
                    tasks[taskIndex].toString();
                    taskIndex++;
                    System.out.println(String.format("You have %d tasks", taskIndex));
                    break;
                case "event":
                    String namee = input.split("event ")[1].split(" /from")[0];
                    String from = input.split("/from ")[1].split(" /to")[0];
                    String to = input.split("/to ")[1];
                    tasks[taskIndex] = new Event(namee, from, to);
                    System.out.println("Added:");
                    tasks[taskIndex].toString();
                    taskIndex++;
                    System.out.println(String.format("You have %d tasks", taskIndex));
                    break;
                default:
//                    if (taskIndex >= MAX_TASKS) {
//                        System.out.println("task list full");
//                        break;
//                    }
//                    tasks[taskIndex] = new Task(input);
//                    taskIndex++;
//                    System.out.println("added: " + input);
            }
        }
        System.out.println("Fine. Bye. Leave and never return");
    }
}
